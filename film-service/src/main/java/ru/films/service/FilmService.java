package ru.films.service;

import ru.films.model.dto.FilmDto;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 04.05.2024
 */
public interface FilmService {
    void addFilms();

    FilmDto getAll();
}
