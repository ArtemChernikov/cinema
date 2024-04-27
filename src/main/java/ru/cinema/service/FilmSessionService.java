package ru.cinema.service;

import ru.cinema.dto.FilmSessionDto;
import ru.cinema.model.FilmSession;

import java.util.Collection;
import java.util.Optional;

/**
 * Общий интерфейс для всех классов-сервисов по работе с киносеансами {@link FilmSession} и DTO {@link FilmSessionDto}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
public interface FilmSessionService {

    Optional<FilmSessionDto> getFilmSessionById(int id);

    Collection<FilmSessionDto> getAllFilmSessions();
}
