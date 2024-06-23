package ru.cinema.service;

import ru.cinema.model.Genre;

import java.util.Optional;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
public interface GenreService {
    Optional<Genre> getGenreById(Long id);
}
