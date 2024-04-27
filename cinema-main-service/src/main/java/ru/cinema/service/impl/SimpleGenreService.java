package ru.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cinema.model.Genre;
import ru.cinema.repository.GenreRepository;
import ru.cinema.service.GenreService;

import java.util.Optional;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
@RequiredArgsConstructor
@Service
public class SimpleGenreService implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public Optional<Genre> getGenreById(int id) {
        return genreRepository.findById(id);
    }
}
