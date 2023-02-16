package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repository.GenreRepository;

import java.util.Optional;

/**
 * Класс-сервис для работы с жанрами {@link Genre}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
@Service
public class SimpleGenreService implements GenreService {

    private final GenreRepository genreRepository;

    public SimpleGenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<Genre> getGenreById(int id) {
        return genreRepository.findById(id);
    }
}
