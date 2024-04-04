package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.repository.sql2o.Sql2oTicketRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class Sql2oTicketRepositoryTest {

    private final Sql2oTicketRepository sql2oTicketRepository;

    private final Sql2o sql2o;

    @Autowired
    Sql2oTicketRepositoryTest(Sql2oTicketRepository sql2oTicketRepository, Sql2o sql2o) {
        this.sql2oTicketRepository = sql2oTicketRepository;
        this.sql2o = sql2o;
    }

    @AfterEach
    public void clearTickets() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM tickets");
            query.executeUpdate();
        }
    }

    /**
     * Метод используется для проверки корректности сохранения билета в БД
     * и поиска билета в БД по киносеансу, ряду и месту
     */
    @Test
    public void whenSaveAndThenGetTicketIsSuccess() {
        var expectedTicket = new Ticket(1, 1, 1, 1);
        var savedTicket = sql2oTicketRepository.save(expectedTicket);
        var findTicket = sql2oTicketRepository.findByRowNumberAndPlaceNumber(1, 1, 1);
        assertThat(savedTicket).isPresent();
        assertThat(findTicket).isPresent();
        assertThat(savedTicket.get()).usingRecursiveComparison().isEqualTo(expectedTicket);
        assertThat(findTicket.get()).usingRecursiveComparison().isEqualTo(expectedTicket);
    }

    /**
     * Метод используется для проверки корректности сохранения билета в БД
     * и поиска билета в БД по киносеансу, ряду и месту
     */
    @Test
    public void whenSaveIsSuccessAndGetTicketIsFiled() {
        var expectedTicket = new Ticket(1, 2, 3, 2);
        var savedTicket = sql2oTicketRepository.save(expectedTicket);
        var findTicket = sql2oTicketRepository.findByRowNumberAndPlaceNumber(1, 1, 1);
        assertThat(savedTicket).isPresent();
        assertThat(findTicket).isEmpty();
        assertThat(savedTicket.get()).usingRecursiveComparison().isEqualTo(expectedTicket);
    }
}