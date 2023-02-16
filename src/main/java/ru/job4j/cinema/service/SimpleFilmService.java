package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.GenreRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Класс-сервис для работы с фильмами {@link Film} и DTO {@link FilmDto}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
@Service
public class SimpleFilmService implements FilmService {

    private final FilmRepository filmRepository;

    private final GenreRepository genreRepository;

    public SimpleFilmService(FilmRepository filmRepository, GenreRepository genreRepository) {
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
    }

    /**
     * Метод используется для получения жанра {@link Genre} фильма {@link Film}
     * из репозитория {@link GenreRepository}
     *
     * @param film - фильм
     * @return - возвращает название жанра
     */
    private String getGenre(Film film) {
        var optionalGenre = genreRepository.findById(film.getGenreId());
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
                film.getDurationInMinutes(), film.getFileId());
    }

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
        return films.stream().map(this::convert).collect(Collectors.toList());
    }
}
