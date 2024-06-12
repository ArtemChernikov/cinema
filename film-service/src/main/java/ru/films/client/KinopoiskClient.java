package ru.films.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.films.config.FeignClientConfiguration;
import ru.films.model.response.KinopoiskApiResponse;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 12.06.2024
 */
@FeignClient(name = "kinopoiskClient", url = "${kinopoisk.api.url}/movie", configuration = FeignClientConfiguration.class)
public interface KinopoiskClient {

    @GetMapping
    KinopoiskApiResponse getFilms(@RequestParam("limit") int limit,
                                         @RequestParam("lists") String[] lists);
}
