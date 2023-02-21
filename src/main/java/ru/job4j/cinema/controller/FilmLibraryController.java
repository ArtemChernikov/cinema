package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.service.FilmService;

import java.util.Collection;

/**
 * Класс-контроллер для работы с фильмами{@link FilmDto} в кинотеке
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 20.02.2023
 */

@ThreadSafe
@Controller
@RequestMapping("/library")
public class FilmLibraryController {

    private final FilmService filmService;

    public FilmLibraryController(FilmService filmService) {
        this.filmService = filmService;
    }

    /**
     * Метод используется для отображения всех фильмов {@link FilmDto}
     * и их описания на странице в кинотеке
     *
     * @param model - модель для добавления фильмов на страницу
     * @return - возвращает отображение со всеми фильмами
     */
    @GetMapping
    public String getFilms(Model model) {
        Collection<FilmDto> films = filmService.getAllFilms();
        model.addAttribute("films", films);
        return "library/list";
    }
}
