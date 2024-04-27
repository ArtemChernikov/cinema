package ru.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cinema.dto.FilmDto;
import ru.cinema.model.Film;
import ru.cinema.model.Genre;
import ru.cinema.repository.FilmRepository;
import ru.cinema.repository.GenreRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Класс-сервис для работы с фильмами {@link Film} и DTO {@link FilmDto}
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

    /**
     * Метод используется для получения фильма {@link Film}
     * из репозитория {@link FilmRepository} по id и дальнейшего его преобразования в DTO {@link FilmDto}
     *
     * @param id - id фильма
     * @return - возвращает DTO {@link FilmDto} обернутый в {@link Optional}
     */
    @Override
    public Optional<FilmDto> getFilmById(int id) {
        var optionalFilm = filmRepository.findById(id);
        if (optionalFilm.isEmpty()) {
            return Optional.empty();
        }
        var film = optionalFilm.get();
        return Optional.of(convert(film));
    }

    /**
     * Метод используется для получения всех фильмов {@link Film} из репозитория {@link FilmRepository}
     * и дальнейшего их преобразования в коллекцию DTO {@link FilmDto}
     *
     * @return - возвращает коллецию всех фильмов в DTO {@link FilmDto} виде
     */
    @Override
    public Collection<FilmDto> getAllFilms() {
        var films = filmRepository.findAll();
        return films.stream().map(this::convert).toList();
    }

    /**
     * Метод используется для получения жанра {@link Genre} фильма {@link Film}
     * из репозитория {@link GenreRepository}
     *
     * @param film - фильм
     * @return - возвращает название жанра
     */
    private String getGenre(Film film) {
        var optionalGenre = genreRepository.findById(film.getGenre().getId());
        return optionalGenre.isPresent() ? optionalGenre.get().getName() : "Неизвестный жанр";
    }

    /**
     * Метод используется для преобразования фильма {@link Film} в DTO {@link FilmDto}
     *
     * @param film - фильм
     * @return - возвращает DTO {@link FilmDto}
     */
    private FilmDto convert(Film film) {
        return new FilmDto(film.getName(), film.getDescription(), film.getYear(),
                getGenre(film), film.getMinimalAge(),
                film.getDurationInMinutes(), film.getFile().getId());
    }
}
