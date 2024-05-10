package ru.cinema.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cinema.model.dto.response.FilmDto;
import ru.cinema.service.impl.FilmServiceImpl;

import java.util.Collection;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 20.02.2023
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/films")
public class FilmController {

    private final FilmServiceImpl filmServiceImpl;

    @GetMapping
    public String getFilms(Model model) {
        log.info("cinema-main-service: выполнение запроса на получение всех фильмов");
        Collection<FilmDto> films = filmServiceImpl.getAllFilms();
        model.addAttribute("films", films);
        log.info("cinema-main-api: выполнен запрос на получение всех фильмов, количество фильмов: {}", films.size());
        return "films/list";
    }

    @GetMapping("/{id}")
    public String getFilmById(@PathVariable Long id, Model model) {
        log.info("cinema-main-service: выполнение запроса на получение фильма по {}", id);
        FilmDto film = filmServiceImpl.getFilmById(id);
        model.addAttribute("film", film);
        log.info("cinema-main-service: выполнен запрос на получение фильма по id: {}, фильм: {}", id, film);
        return "films/one";
    }
}
