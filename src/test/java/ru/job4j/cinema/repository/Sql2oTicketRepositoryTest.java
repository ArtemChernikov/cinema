package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import ru.job4j.cinema.configuration.DataSourceConfiguration;
import ru.job4j.cinema.model.Ticket;

import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oTicketRepositoryTest {

    private static Sql2oTicketRepository sql2oTicketRepository;

    private static Sql2o sql2o;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oFileRepository.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DataSourceConfiguration();
        var connection = configuration.connectionPool(url, username, password);
        sql2o = configuration.databaseClient(connection);

        sql2oTicketRepository = new Sql2oTicketRepository(sql2o);
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