package ru.job4j.cinema.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Ticket;

import java.util.Optional;

/**
 * Класс-репозиторий для работы с билетами {@link Ticket} в БД
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
@ThreadSafe
@Repository
public class Sql2oTicketRepository implements TicketRepository {
    /**
     * Поле экземпляр {@link Sql2o} для работы с БД
     */
    private final Sql2o sql2o;

    public Sql2oTicketRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    /**
     * Метод используется для записи билета {@link Ticket} в БД
     *
     * @param ticket - билет
     * @return - возвращает билет {@link Ticket} обернутый в {@link Optional}
     */
    @Override
    public Optional<Ticket> save(Ticket ticket) {
        try (var connection = sql2o.open()) {
            var sql = """
                    INSERT INTO tickets(session_id, row_number, place_number, user_id)
                    VALUES (:sessionId, :rowNumber, :placeNumber, :userId)
                    """;
            var query = connection.createQuery(sql, true)
                    .addParameter("sessionId", ticket.getSessionId())
                    .addParameter("rowNumber", ticket.getRowNumber())
                    .addParameter("placeNumber", ticket.getPlaceNumber())
                    .addParameter("userId", ticket.getUserId());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            ticket.setId(generatedId);
            return Optional.of(ticket);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * Метод используется для поиска билета {@link Ticket} в БД по id киносеанса, номеру ряда и номеру места
     *
     * @param filmSessionId - id киносеанса
     * @param rowNumber     - номер ряда
     * @param placeNumber   - номер места
     * @return - возвращает билет {@link Ticket} обернутый в {@link Optional}
     */
    @Override
    public Optional<Ticket> findByRowNumberAndPlaceNumber(int filmSessionId, int rowNumber, int placeNumber) {
        try (var connection = sql2o.open()) {
            var sql = """
                    SELECT * FROM tickets WHERE session_id = :filmSessionId AND row_number = :rowNumber
                    AND place_number = :placeNumber
                    """;
            var query = connection.createQuery(sql)
                    .addParameter("filmSessionId", filmSessionId)
                    .addParameter("rowNumber", rowNumber)
                    .addParameter("placeNumber", placeNumber);
            var ticket = query.setColumnMappings(Ticket.COLUMN_MAPPING).executeAndFetchFirst(Ticket.class);
            return Optional.ofNullable(ticket);
        }
    }
}
