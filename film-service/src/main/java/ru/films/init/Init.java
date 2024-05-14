package ru.films.init;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.films.service.FilmService;

import javax.annotation.PostConstruct;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 14.05.2024
 */
@RequiredArgsConstructor
@Component
public class Init {

    private final FilmService filmService;

    @PostConstruct
    public void addFilms() {
        filmService.addFilms();
    }
}
