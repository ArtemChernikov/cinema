package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;
import java.util.Optional;

/**
 * Общий интерфейс для всех репозиториев с киносеансами {@link FilmSession}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
public interface FilmSessionRepository {
    Optional<FilmSession> findById(int id);

    Collection<FilmSession> findAll();
}
