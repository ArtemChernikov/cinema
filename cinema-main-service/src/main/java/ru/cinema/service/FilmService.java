package ru.cinema.service;

import ru.cinema.model.dto.FilmDto;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
public interface FilmService {

    Optional<FilmDto> getFilmById(long id);

    Collection<FilmDto> getAllFilms();

}
