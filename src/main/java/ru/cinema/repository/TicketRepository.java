package ru.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cinema.model.Ticket;

import java.util.Optional;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findByFilmSessionIdAndRowNumberAndPlaceNumber(Integer filmSessionId, Integer rowNumber,
                                                                   Integer placeNumber);
}
