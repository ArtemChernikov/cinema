package ru.films.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.films.client.KinopoiskClient;
import ru.films.exception.FilmNotFoundException;
import ru.films.model.Film;
import ru.films.model.dto.FilmDto;
import ru.films.model.response.Document;
import ru.films.model.response.Response;
import ru.films.repository.FilmRepository;
import ru.films.utils.mapper.FilmMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.films.exception.message.FilmExceptionMessage.FILM_NOT_FOUND;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 06.05.2024
 */
@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final KinopoiskClient kinopoiskClient;
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;
    private final ObjectMapper objectMapper;

    public void addFilms() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Document> documents = objectMapper
                .convertValue(kinopoiskClient.getPopularFilms().getBody(), Response.class).getDocs();
        List<Film> films = filmMapper.documentListToFilmList(cleanDocuments(documents));
        saveNewFilms(films);
    }

    public List<FilmDto> getAll() {
        return filmMapper.filmListToFilmDtoList(filmRepository.findAll());
    }

    @Override
    public FilmDto getById(Long id) {
        return filmMapper.filmToFilmDto(filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException(FILM_NOT_FOUND)));
    }

    private void saveNewFilms(List<Film> films) {
        Set<Long> filmIds = films.stream()
                .map(Film::getId)
                .collect(Collectors.toSet());
        List<Film> existingFilms = filmRepository.findAllById(filmIds);
        Set<Long> existingFilmIds = existingFilms.stream()
                .map(Film::getId)
                .collect(Collectors.toSet());
        List<Film> newFilms = films.stream()
                .filter(film -> !existingFilmIds.contains(film.getId()))
                .toList();
        if (!newFilms.isEmpty()) {
            filmRepository.saveAll(newFilms);
        }
    }

    private List<Document> cleanDocuments(List<Document> documents) {
        return documents.stream()
                .filter(this::isValidDocument)
                .toList();
    }

    private boolean isValidDocument(Document document) {
        if (document == null) {
            return false;
        }
        return document.getId() != null
                && document.getName() != null
                && document.getAlternativeName() != null
                && document.getType() != null
                && document.getYear() != null
                && document.getDescription() != null
                && document.getShortDescription() != null
                && document.getRating() != null
                && document.getMovieLength() != null
                && document.getAgeRating() != null
                && document.getPoster() != null
                && document.getBackdrop() != null
                && document.getGenres() != null && !document.getGenres().isEmpty()
                && document.getCountries() != null && !document.getCountries().isEmpty();
    }
}
