package ru.cinema.service;

import ru.cinema.model.dto.RequestAddFilms;
import ru.cinema.model.dto.response.FilmDto;

import java.util.List;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 18.05.2024
 */
public interface FilmService {
    List<FilmDto> getAllFilms();

    FilmDto getFilmById(Long id);

    void addFilms(RequestAddFilms requestAddFilms);

}
