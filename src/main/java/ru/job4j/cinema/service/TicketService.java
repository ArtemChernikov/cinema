package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Ticket;

import java.util.Optional;

/**
 * Общий интерфейс для всех классов-сервисов по работе с билетами {@link Ticket}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
public interface TicketService {
    Optional<Ticket> save(Ticket ticket);
}
