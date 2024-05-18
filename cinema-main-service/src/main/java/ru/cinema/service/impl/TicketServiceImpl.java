package ru.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cinema.exception.TicketExistsException;
import ru.cinema.model.Ticket;
import ru.cinema.model.dto.TicketDto;
import ru.cinema.repository.TicketRepository;
import ru.cinema.service.TicketService;
import ru.cinema.utils.mapper.TicketMapper;

import java.util.Optional;

import static ru.cinema.exception.message.TicketExceptionMessage.TICKET_ALREADY_EXISTS;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    public Ticket save(TicketDto ticketDto) {
        Optional<Ticket> ticket = ticketRepository.findByFilmSessionIdAndRowNumberAndPlaceNumber(
                ticketDto.getFilmSessionId(), ticketDto.getRowNumber(), ticketDto.getPlaceNumber());
        if (ticket.isPresent()) {
            throw new TicketExistsException(TICKET_ALREADY_EXISTS);
        }
        return ticketRepository.save(ticketMapper.ticketDtoToTicket(ticketDto));
    }

}
