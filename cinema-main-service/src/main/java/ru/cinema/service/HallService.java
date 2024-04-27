package ru.cinema.service;

import ru.cinema.model.Hall;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
public interface HallService {
    Optional<Hall> getHallById(int id);

    Collection<Hall> getAllHalls();

    List<Integer> getRows(Hall hall);

    List<Integer> getPlaces(Hall hall);
}
