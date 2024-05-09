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
import java.util.Optional;

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
        log.info("cinema-main-api: выполнение запроса на получение всех фильмов");
        Collection<FilmDto> films = filmServiceImpl.getAllFilms();
        model.addAttribute("films", films);
        log.info("cinema-main-api: выполнен запрос на получение всех фильмов, количество фильмов: {}", films.size());
        return "films/list";
    }

    @GetMapping("/{id}")
    public String getFilmById(@PathVariable Long id, Model model) {
        log.info("cinema-main-api: выполнение запроса на получение фильма по {}", id);
        Optional<FilmDto> film = filmServiceImpl.getFilmById(id);
        if (film.isEmpty()) {
            model.addAttribute("message", "Фильм не найден");
            return "errors/404";
        }
        model.addAttribute("film", film.get());
        log.info("cinema-main-api: выполнен запрос на получение фильма по id: {}, фильм: {}", id, film.get());
        return "films/one";
    }
}
