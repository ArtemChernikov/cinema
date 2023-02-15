package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.Optional;

/**
 * Общий интерфейс для всех классов-сервисов по работе с фильмами {@link Film} и DTO {@link FilmDto}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
public interface FilmService {

    Optional<FilmDto> getFilmById(int id);

    Collection<FilmDto> getAllFilms();
}
