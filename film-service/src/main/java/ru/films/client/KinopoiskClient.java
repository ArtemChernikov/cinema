package ru.films.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Service
public class KinopoiskClient extends BaseClient {
    private static final String API_PREFIX = "/movie";

    @Autowired
    public KinopoiskClient(RestTemplateBuilder builder, @Value("${kinopoisk.api.url}") String serverUrl,
                           @Value("${kinopoisk.api.key}") String apiKey,
                           @Value("${kinopoisk.api.headers}") String headers) {
        super(
                builder.uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build(),
                apiKey, headers
        );
    }

    public ResponseEntity<Object> getPopularFilms() {
        Map<String, Object> parameters = Map.of(
                "limit", 250,
                "lists", new String[]{"popular-films"}
        );
        return get("?limit={limit}&lists={lists}", parameters);
    }

}
