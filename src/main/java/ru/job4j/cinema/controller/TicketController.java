package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.HallService;
import ru.job4j.cinema.service.TicketService;
import ru.job4j.cinema.dto.FilmSessionDto;

/**
 * Класс-контроллер для работы с билетами {@link Ticket}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 25.02.2023
 */
@ThreadSafe
@Controller
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    private final FilmSessionService filmSessionService;

    private final HallService hallService;

    public TicketController(TicketService ticketService, FilmSessionService filmSessionService, HallService hallService) {
        this.ticketService = ticketService;
        this.filmSessionService = filmSessionService;
        this.hallService = hallService;
    }

    /**
     * Метод используется для вывода на страницу по id киносеанса {@link FilmSessionDto}
     * отображения с выбором ряда и места и дальнейшей покупки билета
     *
     * @param model - модель для добавления данных на страницу
     * @param id    - id киносеанса {@link FilmSessionDto}
     * @return - возвращает страницу с покупкой билета {@link Ticket}
     */
    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var optFilmSession = filmSessionService.getFilmSessionById(id);
        if (optFilmSession.isEmpty()) {
            model.addAttribute("message", "Указанный киносеанс не найден");
            return "errors/404";
        }
        var filmSession = optFilmSession.get();
        var hall = hallService.getHallById(filmSession.getHallId()).get();
        var rows = hallService.getRows(hall);
        var places = hallService.getPlaces(hall);
        model.addAttribute("rows", rows);
        model.addAttribute("places", places);
        model.addAttribute("filmSession", filmSession);
        model.addAttribute("ticket", new Ticket());
        return "tickets/buyTicket";
    }

    /**
     * Метод используется для покупки билета {@link Ticket} и сохранения его в БД
     *
     * @param ticket - билет
     * @param model  - модель для вывода данных на страницу
     * @return - возвращает страницу с успешной покупкой билета
     */
    @PostMapping("/buy")
    public String buyTicket(@ModelAttribute Ticket ticket, Model model) {
        try {
            var optSavedTicket = ticketService.save(ticket);
            if (optSavedTicket.isEmpty()) {
                model.addAttribute("message", """
                        Не удалось приобрести билет на заданное место. Вероятно оно уже занято.
                        Перейдите на страницу бронирования билетов и попробуйте снова.
                        """);
                return "errors/404";
            }
            model.addAttribute("ticket", ticket);
            model.addAttribute("filmSession", filmSessionService.getFilmSessionById(ticket.getSessionId()).get());
            return "success/201";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "errors/404";
        }
    }
}
