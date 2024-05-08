package ru.cinema.controller;

import lombok.RequiredArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cinema.model.dto.response.FilmDto;
import ru.cinema.service.impl.FilmServiceImpl;

import java.util.Collection;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 20.02.2023
 */
@ThreadSafe
@RequiredArgsConstructor
@Controller
@RequestMapping("/films")
public class FilmController {

    private final FilmServiceImpl filmServiceImpl;

    @GetMapping
    public String getFilms(Model model) {
        Collection<FilmDto> films = filmServiceImpl.getAllFilms();
        model.addAttribute("films", films);
        return "films/list";
    }
}
