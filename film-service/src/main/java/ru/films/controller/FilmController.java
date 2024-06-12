package ru.films.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.films.model.dto.FilmDto;
import ru.films.service.FilmService;

import java.util.List;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 28.04.2024
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    @PostMapping("/add-popular-films")
    public void addPopularFilms() {
        log.info("film-api: выполнение запроса на добавление популярных фильмов из kinopoisk API");
        filmService.addFilms();
        log.info("film-api: выполен запрос на добавление популярных фильмов из kinopoisk API");
    }

    @GetMapping
    public List<FilmDto> getAll() {
        log.info("film-api: выполнение запроса на получение всех фильмов");
        List<FilmDto> films = filmService.getAll();
        log.info("film-api: выполнен запрос на получение всех фильмов, количество фильмов: {}", films.size());
        return films;
    }

    @GetMapping("/{id}")
    public FilmDto getFilmById(@PathVariable long id) {
        log.info("film-api: выполнение запроса на получение фильма по id: {}", id);
        FilmDto film = filmService.getById(id);
        log.info("film-api: выполнен запрос на получение фильма по id: {}, фильм: {}", id, film);
        return film;
    }
}
