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
import ru.cinema.model.User;
import ru.cinema.model.dto.FilmSessionDto;
import ru.cinema.model.dto.TicketDto;
import ru.cinema.service.FilmSessionService;
import ru.cinema.service.TicketService;
import ru.cinema.service.UserService;

import java.security.Principal;
import java.util.Optional;

/**
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
    private final UserService userService;

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable("id") long id, Principal principal) {
        FilmSessionDto filmSession = filmSessionService.getFilmSessionById(id);
        Optional<User> optionalUser = userService.getByUsername(principal.getName());
        optionalUser.ifPresent(user -> model.addAttribute("user", user));
        model.addAttribute("rows", filmSession.getHallDto().getRows());
        model.addAttribute("places", filmSession.getHallDto().getPlaces());
        model.addAttribute("filmSession", filmSession);
        model.addAttribute("ticket", new TicketDto());
        return "tickets/buyTicket";
    }

    @PostMapping("/buy")
    public String buyTicket(@ModelAttribute TicketDto ticketDto, Model model) {
        ticketService.save(ticketDto);
        model.addAttribute("ticket", ticketDto);
        model.addAttribute("filmSession", filmSessionService
                .getFilmSessionById(ticketDto.getFilmSessionId()));
        return "success/201";
    }
}
