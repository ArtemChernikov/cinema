package ru.cinema.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.cinema.model.dto.response.FilmDto;

import java.util.List;

@FeignClient(name = "filmServiceClient", url = "${films.api.url}")
public interface FilmApiClient {

    @GetMapping("/films")
    List<FilmDto> getAllFilms();

    @GetMapping("/films/{id}")
    FilmDto getFilmById(@PathVariable long id);
}
