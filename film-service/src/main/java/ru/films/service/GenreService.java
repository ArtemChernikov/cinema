package ru.films.service;

import ru.films.model.Genre;

import java.util.Map;
import java.util.Set;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 12.06.2024
 */
public interface GenreService {
    Map<String, Genre> saveNotExistsGenre(Set<String> genreNames);
}
