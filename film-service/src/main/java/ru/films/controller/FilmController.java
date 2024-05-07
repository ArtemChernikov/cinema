package ru.films.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.films.service.FilmService;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 28.04.2024
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    @PostMapping("/add-popular-films")
    public void addPopularFilms() {
        filmService.addFilms();
    }
}
