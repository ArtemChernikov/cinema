package ru.films.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.films.model.Genre;
import ru.films.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 12.06.2024
 */
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public Map<String, Genre> saveNotExistsGenre(Set<String> genreNames) {
        List<Genre> existingGenres = genreRepository.findByNameIn(new ArrayList<>(genreNames));
        Set<String> existingGenreNames = existingGenres.stream()
                .map(Genre::getName)
                .collect(Collectors.toSet());
        List<Genre> newGenres = genreNames.stream()
                .filter(name -> !existingGenreNames.contains(name))
                .map(name -> Genre.builder().name(name).build())
                .toList();
        newGenres = genreRepository.saveAll(newGenres);
        return Stream.concat(existingGenres.stream(), newGenres.stream())
                .collect(Collectors.toMap(Genre::getName, Function.identity()));
    }
}
