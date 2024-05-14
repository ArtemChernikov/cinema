package ru.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cinema.model.Film;
import ru.cinema.model.dto.FilmDto;
import ru.cinema.repository.FilmRepository;
import ru.cinema.repository.GenreRepository;
import ru.cinema.service.FilmService;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
@RequiredArgsConstructor
@Service
public class SimpleFilmService implements FilmService {

    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;

    @Override
    public Optional<FilmDto> getFilmById(int id) {
        var optionalFilm = filmRepository.findById(id);
        if (optionalFilm.isEmpty()) {
            return Optional.empty();
        }
        var film = optionalFilm.get();
        return Optional.of(convert(film));
    }

    @Override
    public Collection<FilmDto> getAllFilms() {
        var films = filmRepository.findAll();
        return films.stream().map(this::convert).toList();
    }


    private String getGenre(Film film) {
        var optionalGenre = genreRepository.findById(film.getGenre().getId());
        return optionalGenre.isPresent() ? optionalGenre.get().getName() : "Неизвестный жанр";
    }

    private FilmDto convert(Film film) {
        return new FilmDto(film.getName(), film.getDescription(), film.getYear(),
                getGenre(film), film.getMinimalAge(),
                film.getDurationInMinutes());
    }
}
