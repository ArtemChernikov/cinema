package ru.cinema.service;

import ru.cinema.model.Ticket;
import ru.cinema.model.dto.TicketDto;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
public interface TicketService {
    Ticket save(TicketDto ticketDto);
}
