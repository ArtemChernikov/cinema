package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Genre;

import java.util.Optional;

/**
 * Общий интерфейс для всех репозиториев с жанрами {@link Genre}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
public interface GenreRepository {
    Optional<Genre> findById(int id);
}
