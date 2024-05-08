package ru.cinema.service;

import ru.cinema.model.dto.TicketDto;
import ru.cinema.model.Ticket;

import java.util.Optional;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
public interface TicketService {
    Optional<Ticket> save(TicketDto ticketDto);
}
