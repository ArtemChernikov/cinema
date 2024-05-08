package ru.cinema.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
public class FilmClient extends BaseClient {
    private static final String API_PREFIX = "/films";

    @Autowired
    public FilmClient(RestTemplateBuilder builder, @Value("${films.api.url}") String serverUrl) {
        super(
                builder.uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> getAllFilms() {
        return get("");
    }

    public ResponseEntity<Object> getFilmById(Long id) {
        return get(String.valueOf(id));
    }

}
