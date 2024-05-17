package ru.cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cinema.model.dto.FilmSessionCreateDto;
import ru.cinema.service.FilmSessionService;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 17.05.2024
 */
@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

    private final FilmSessionService filmSessionService;

    @PostMapping("/add-film-session")
    public String addFilmSession(@ModelAttribute FilmSessionCreateDto filmSessionCreateDto) {
        filmSessionService.createFilmSession(filmSessionCreateDto);
        return "redirect:/films";
    }

}
