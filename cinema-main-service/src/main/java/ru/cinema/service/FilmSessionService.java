package ru.cinema.service;

import ru.cinema.model.dto.FilmSessionCreateDto;
import ru.cinema.model.dto.FilmSessionDto;

import java.util.Collection;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
public interface FilmSessionService {

    void createFilmSession(FilmSessionCreateDto filmSessionCreateDto);

    FilmSessionDto getFilmSessionById(Long id);

    Collection<FilmSessionDto> getAllFilmSessions();
}
