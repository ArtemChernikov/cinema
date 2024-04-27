package ru.cinema.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cinema.dto.FilmSessionDto;
import ru.cinema.model.Hall;
import ru.cinema.model.Ticket;
import ru.cinema.model.User;
import ru.cinema.dto.TicketDto;
import ru.cinema.service.FilmSessionService;
import ru.cinema.service.HallService;
import ru.cinema.service.TicketService;
import ru.cinema.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Класс-контроллер для работы с билетами {@link Ticket}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 25.02.2023
 */
@ThreadSafe
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    private final FilmSessionService filmSessionService;

    private final HallService hallService;

    private final UserService userService;

    /**
     * Метод используется для вывода на страницу по id киносеанса {@link FilmSessionDto}
     * отображения с выбором ряда и места и дальнейшей покупки билета
     *
     * @param model - модель для добавления данных на страницу
     * @param id    - id киносеанса {@link FilmSessionDto}
     * @return - возвращает страницу с покупкой билета {@link Ticket}
     */
    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id, Principal principal) {
        Optional<FilmSessionDto> optFilmSession = filmSessionService.getFilmSessionById(id);
        if (optFilmSession.isEmpty()) {
            model.addAttribute("message", "Указанный киносеанс не найден");
            return "errors/404";
        }
        Optional<User> optionalUser = userService.getByUsername(principal.getName());
        optionalUser.ifPresent(user -> model.addAttribute("user", user));
        FilmSessionDto filmSession = optFilmSession.get();
        Hall hall = hallService.getHallById(filmSession.getHallId()).get();
        List<Integer> rows = hallService.getRows(hall);
        List<Integer> places = hallService.getPlaces(hall);
        model.addAttribute("rows", rows);
        model.addAttribute("places", places);
        model.addAttribute("filmSession", filmSession);
        model.addAttribute("ticket", new TicketDto());
        return "tickets/buyTicket";
    }

    /**
     * Метод используется для покупки билета {@link Ticket} и сохранения его в БД
     *
     * @param ticketDto - билет
     * @param model     - модель для вывода данных на страницу
     * @return - возвращает страницу с успешной покупкой билета
     */
    @PostMapping("/buy")
    public String buyTicket(@ModelAttribute TicketDto ticketDto, Model model) {
        try {
            var optSavedTicket = ticketService.save(ticketDto);
            if (optSavedTicket.isEmpty()) {
                model.addAttribute("message", """
                        Не удалось приобрести билет на заданное место. Вероятно оно уже занято.
                        Перейдите на страницу бронирования билетов и попробуйте снова.
                        """);
                return "errors/404";
            }
            model.addAttribute("ticket", ticketDto);
            model.addAttribute("filmSession", filmSessionService
                    .getFilmSessionById(ticketDto.getFilmSessionId()).get());
            return "success/201";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            model.addAttribute("message", e.getMessage());
            return "errors/404";
        }
    }
}
