package ru.cinema.service;

import ru.cinema.model.Genre;

import java.util.Optional;

/**
 * Общий интерфейс для всех классов-сервисов по работе с жанрами {@link Genre}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
public interface GenreService {
    Optional<Genre> getGenreById(int id);
}
