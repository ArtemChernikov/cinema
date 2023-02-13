package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Hall;

import java.util.Collection;
import java.util.Optional;

/**
 * Общий интерфейс для всех репозиториев с залами {@link Hall}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
public interface HallRepository {
    Optional<Hall> findById(int id);

    Collection<Hall> findAll();
}
