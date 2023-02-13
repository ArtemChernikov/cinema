package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.Optional;

/**
 * Общий интерфейс для всех репозиториев с фильмами {@link Film}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
public interface FilmRepository {
    Optional<Film> findById(int id);

    Collection<Film> findAll();
}
