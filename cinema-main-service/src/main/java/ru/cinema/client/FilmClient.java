package ru.cinema.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.cinema.exception.ApiError;
import ru.cinema.exception.NotFoundException;
import ru.cinema.model.dto.response.FilmDto;

import java.util.List;

@Slf4j
@Service
public class FilmClient extends BaseClient {
    private final ObjectMapper objectMapper;
    private static final String API_PREFIX = "/films";

    @Autowired
    public FilmClient(RestTemplateBuilder builder,
                      @Value("${films.api.url}") String serverUrl,
                      ObjectMapper objectMapper) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault()))
                .build()
        );
        this.objectMapper = objectMapper;
    }

    public List<FilmDto> getAllFilms() {
        ResponseEntity<Object> response = get("");
        return objectMapper.convertValue(response.getBody(), new TypeReference<>() {});
    }

    public FilmDto getFilmById(Long id) {
        ResponseEntity<Object> response = get("/" + id);
        HttpStatus statusCode = HttpStatus.valueOf(response.getStatusCode().value());
        if (!statusCode.is2xxSuccessful()) {
            checkStatusCode(response, statusCode);
        }
        return objectMapper.convertValue(response.getBody(), FilmDto.class);
    }

    private void checkStatusCode(ResponseEntity<Object> response, HttpStatus httpStatus) {
        try {
            ApiError apiError = objectMapper.readValue((String) response.getBody(), ApiError.class);
            if (httpStatus.equals(HttpStatus.NOT_FOUND)) {
                log.warn("cinema-main-service FilmClient: " + apiError);
                throw new NotFoundException(apiError.getMessage());
            }
        } catch (JsonProcessingException e) {
            log.warn("cinema-main-service FilmClient: " + e.getMessage());
        }
    }

}
