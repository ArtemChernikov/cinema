package ru.cinema.service;

import ru.cinema.model.dto.FilmSessionDto;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
public interface FilmSessionService {

    Optional<FilmSessionDto> getFilmSessionById(long id);

    Collection<FilmSessionDto> getAllFilmSessions();
}
