package ru.cinema.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cinema.service.FilmSessionService;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 22.02.2023
 */
@ThreadSafe
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/filmSessions")
public class FilmSessionController {

    private final FilmSessionService filmSessionService;

    @GetMapping
    public String getFilmSessions(Model model) {
        log.info("cinema-main-service: выполнение запроса на получение всех кинсоеансов");
        var filmSessions = filmSessionService.getAllFilmSessions();
        model.addAttribute("filmSessions", filmSessions);
        return "filmSessions/list";
    }
}
