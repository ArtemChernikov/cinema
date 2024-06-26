package ru.cinema.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cinema.model.dto.FilmSessionCreateDto;
import ru.cinema.model.dto.HallShortDto;
import ru.cinema.model.dto.RequestAddFilms;
import ru.cinema.model.dto.response.FilmDto;
import ru.cinema.service.HallService;
import ru.cinema.service.impl.FilmServiceImpl;

import java.util.Collection;
import java.util.List;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 20.02.2023
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/films")
public class FilmController {

    private final FilmServiceImpl filmServiceImpl;
    private final HallService hallService;

    @GetMapping
    public String getFilms(Model model) {
        log.info("cinema-main-service: выполнение запроса на получение всех фильмов");
        Collection<FilmDto> films = filmServiceImpl.getAllFilms();
        model.addAttribute("films", films);
        if (isAdmin()) {
            List<HallShortDto> halls = hallService.getAllHallsAsDto();
            model.addAttribute("halls", halls);
            model.addAttribute("newFilmSession", new FilmSessionCreateDto());
            model.addAttribute("filmsCollections", new String[]{"popular-films"});
            model.addAttribute("requestAddFilms", new RequestAddFilms());
        }
        log.info("cinema-main-service: выполнен запрос на получение всех фильмов, количество фильмов: {}", films.size());
        return "films/list";
    }

    @GetMapping("/{id}")
    public String getFilmById(@PathVariable("id") Long id, Model model) {
        log.info("cinema-main-service: выполнение запроса на получение фильма по {}", id);
        FilmDto film = filmServiceImpl.getFilmById(id);
        model.addAttribute("film", film);
        log.info("cinema-main-service: выполнен запрос на получение фильма по id: {}, фильм: {}", id, film);
        return "films/one";
    }

    private boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() &&
                authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> "ROLE_ADMIN".equals(grantedAuthority.getAuthority()));
    }
}
