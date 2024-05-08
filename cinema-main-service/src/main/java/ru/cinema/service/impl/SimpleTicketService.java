package ru.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cinema.model.dto.TicketDto;
import ru.cinema.model.FilmSession;
import ru.cinema.model.Ticket;
import ru.cinema.model.User;
import ru.cinema.repository.FilmSessionRepository;
import ru.cinema.repository.TicketRepository;
import ru.cinema.service.TicketService;
import ru.cinema.service.UserService;

import java.util.Optional;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
@RequiredArgsConstructor
@Service
public class SimpleTicketService implements TicketService {

    private final UserService userService;
    private final FilmSessionRepository filmSessionRepository;
    private final TicketRepository ticketRepository;

    @Override
    public Optional<Ticket> save(TicketDto ticketDto) {
        //TODO сделать нормальный маппинг
        Optional<FilmSession> optionalFilmSession = filmSessionRepository
                .findById(ticketDto.getFilmSessionId());
        Optional<User> optionalUser = userService.getById(ticketDto.getUserId());
        if (optionalFilmSession.isEmpty() || optionalUser.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Optional<Ticket> ticket = ticketRepository.findByFilmSessionIdAndRowNumberAndPlaceNumber(
                ticketDto.getFilmSessionId(), ticketDto.getRowNumber(), ticketDto.getPlaceNumber());
        if (ticket.isPresent()) {
            return Optional.empty();
        }
        Ticket newTicket = Ticket.builder()
                .filmSession(optionalFilmSession.get())
                .user(optionalUser.get())
                .placeNumber(ticketDto.getPlaceNumber())
                .rowNumber(ticketDto.getRowNumber())
                .build();
        return Optional.of(ticketRepository.save(newTicket));
    }

}
