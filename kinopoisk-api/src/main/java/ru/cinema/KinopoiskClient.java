package ru.cinema;

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

    public ResponseEntity<Object> getTop10Films() {
        Map<String, Object> parameters = Map.of(
                "page", 1,
                "limit", 10,
                "year", new int[]{2018},
                "lists", new String[]{"popular-films"}
        );
        return get("?page={page}&limit={limit}&year={year}&lists={lists}", parameters);
    }

}
