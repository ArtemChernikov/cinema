package ru.films.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.films.client.KinopoiskClient;
import ru.films.model.response.Response;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 28.04.2024
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/kinopoisk")
public class FilmController {
    private final KinopoiskClient kinopoiskClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PatchMapping("/update")
    public void updateFilms() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Response response = objectMapper.convertValue(kinopoiskClient.getTop10Films().getBody(), Response.class);
        System.out.println("Total documents: " + response.getTotal());
        System.out.println(response);
    }
}
