package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.HallService;
import ru.job4j.cinema.service.TicketService;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

class TicketControllerTest {

    private TicketService ticketService;

    private FilmSessionService filmSessionService;

    private HallService hallService;

    private TicketController ticketController;

    @BeforeEach
    public void initService() {
        ticketService = mock(TicketService.class);
        filmSessionService = mock(FilmSessionService.class);
        hallService = mock(HallService.class);
        ticketController = new TicketController(ticketService, filmSessionService, hallService);
    }

    /**
     * Метод используется для проверки корректности работы контроллера по выводу страницы покупки билета в отображение
     */
    @Test
    public void whenRequestBuyTicketPageThenGetBuyTicketPage() {
        var expectedFilmSession = new FilmSessionDto(1, "film1", "hall1", 1, LocalDateTime.now(), LocalDateTime.now().plusHours(2), 300);
        var expectedHall = new Hall("hall1", 2, 3, "description1", 1);
        var expectedRows = List.of(1, 2);
        var expectedPlaces = List.of(1, 2, 3);
        var expectedTicket = new Ticket();
        when(filmSessionService.getFilmSessionById(any(Integer.class))).thenReturn(Optional.of(expectedFilmSession));
        when(hallService.getHallById(any(Integer.class))).thenReturn(Optional.of(expectedHall));
        when(hallService.getRows(any(Hall.class))).thenReturn(expectedRows);
        when(hallService.getPlaces(any(Hall.class))).thenReturn(expectedPlaces);

        var model = new ConcurrentModel();
        var view = ticketController.getById(model, 1);
        var actualRows = model.getAttribute("rows");
        var actualPlaces = model.getAttribute("places");
        var actualFilmSession = model.getAttribute("filmSession");
        var actualTicket = model.getAttribute("ticket");

        assertThat(view).isEqualTo("tickets/buyTicket");
        assertThat(actualRows).isEqualTo(expectedRows);
        assertThat(actualPlaces).isEqualTo(expectedPlaces);
        assertThat(actualFilmSession).isEqualTo(expectedFilmSession);
        assertThat(actualTicket).isEqualTo(expectedTicket);
    }

    /**
     * Метод используется для проверки корректности работы контроллера
     * по выводу страницы покупки билета в отображение (выдает ошибку по ненайденному киносеансу)
     */
    @Test
    public void whenRequestBuyTicketPageThenGetError() {
        var expectedMessage = "Указанный киносеанс не найден";
        when(filmSessionService.getFilmSessionById(any(Integer.class))).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var view = ticketController.getById(model, 1);
        var actualMessage = model.getAttribute("message");

        assertThat(view).isEqualTo("errors/404");
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    /**
     * Метод проверяет корректность работы контроллера по выводу уведомления об успешной покупке билета в отображение
     */
    @Test
    public void whenResponseBuyTicketThenGetSuccessPage() {
        var ticket = new Ticket(1, 1, 1, 1);
        var ticketArgumentCapture = ArgumentCaptor.forClass(Ticket.class);
        var expectedMessage = "Вы успешно приобрели билет." + System.lineSeparator() + "Ваш ряд: 1"
                + System.lineSeparator() + "Ваше место: 1";
        when(ticketService.save(ticketArgumentCapture.capture())).thenReturn(Optional.of(ticket));

        var model = new ConcurrentModel();
        var view = ticketController.buyTicket(ticket, model);
        var actualMessage = model.getAttribute("message");
        var actualTicket = ticketArgumentCapture.getValue();

        assertThat(view).isEqualTo("success/201");
        assertThat(actualMessage).isEqualTo(expectedMessage);
        assertThat(actualTicket).usingRecursiveComparison().isEqualTo(ticket);
    }

    /**
     * Метод используется для проверки работы контроллера
     * по выводу уведомления об ошибке, при покупке билета на уже занятое место
     */
    @Test
    public void whenResponseBuyTicketThenGetErrorPage() {
        var ticket = new Ticket(1, 1, 1, 1);
        var ticketArgumentCapture = ArgumentCaptor.forClass(Ticket.class);
        var expectedMessage = """
                Не удалось приобрести билет на заданное место. Вероятно оно уже занято.
                Перейдите на страницу бронирования билетов и попробуйте снова.
                """;
        when(ticketService.save(ticketArgumentCapture.capture())).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var view = ticketController.buyTicket(ticket, model);
        var actualMessage = model.getAttribute("message");
        var actualTicket = ticketArgumentCapture.getValue();

        assertThat(view).isEqualTo("errors/404");
        assertThat(actualTicket).usingRecursiveComparison().isEqualTo(ticket);
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

}