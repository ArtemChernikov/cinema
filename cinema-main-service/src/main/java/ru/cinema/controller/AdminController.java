package ru.cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cinema.model.dto.FilmSessionDto;
import ru.cinema.model.dto.response.FilmDto;
import ru.cinema.service.impl.FilmServiceImpl;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 17.05.2024
 */
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final FilmServiceImpl filmService;

//    @GetMapping("/new-film-session/{filmId}")
//    public String getNewFilmSessionPage(@PathVariable long filmId, Model model) {
//        FilmDto film = filmService.getFilmById(filmId);
//        model.addAttribute("newFilmSession", new FilmSessionDto());
//        model.addAttribute("filmName", film.getName());
//        return "redirect:/films";
//    }

    @PostMapping("/add-film-session/{filmId}")
    public String addFilmSession(@PathVariable long filmId, Model model) {
        FilmDto film = filmService.getFilmById(filmId);
        model.addAttribute("newFilmSession", new FilmSessionDto());
        model.addAttribute("filmName", film.getName());
        return "redirect:/films";
    }
}
