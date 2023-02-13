package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Ticket;

import java.util.Optional;

/**
 * Общий интерфейс для всех репозиториев с билетами {@link Ticket}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
public interface TicketRepository {
    Optional<Ticket> save(Ticket ticket);

    Optional<Ticket> findByRowNumberAndPlaceNumber(int filmSessionId, int rowNumber, int placeNumber);
}
