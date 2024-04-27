package ru.cinema.controller;

import lombok.RequiredArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cinema.service.FilmSessionService;
import ru.cinema.dto.FilmSessionDto;

/**
 * Класс-контроллер для работы с киносеансами {@link FilmSessionDto}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 22.02.2023
 */
@ThreadSafe
@RequiredArgsConstructor
@Controller
@RequestMapping("/filmSessions")
public class FilmSessionController {

    private final FilmSessionService filmSessionService;

    /**
     * Метод используется для отображения всех киносеансов {@link FilmSessionDto}
     *
     * @param model - модель для добавления киносеансов на страницу
     * @return - возвращает отображение со всеми киносеансами
     */
    @GetMapping
    public String getFilmSessions(Model model) {
        var filmSessions = filmSessionService.getAllFilmSessions();
        model.addAttribute("filmSessions", filmSessions);
        return "filmSessions/list";
    }
}
