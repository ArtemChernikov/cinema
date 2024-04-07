package ru.job4j.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.TicketDto;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.repository.FilmSessionRepository;
import ru.job4j.cinema.repository.TicketRepository;

import java.util.Optional;

/**
 * Класс-сервис для работы с билетами {@link Ticket}
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
