package ru.cinema.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.cinema.config.FeignClientConfiguration;
import ru.cinema.model.dto.response.FilmDto;

import java.util.List;

@FeignClient(name = "filmServiceApiClient",
        url = "${films.api.url}",
        configuration = FeignClientConfiguration.class)
public interface FilmServiceApiClient {

    @GetMapping("/films")
    List<FilmDto> getAllFilms();

    @GetMapping("/films/{id}")
    FilmDto getFilmById(@PathVariable("id") Long id);
}
