package ru.films.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.films.client.KinopoiskApiClient;
import ru.films.exception.FilmNotFoundException;
import ru.films.model.Country;
import ru.films.model.Film;
import ru.films.model.Genre;
import ru.films.model.dto.FilmDto;
import ru.films.model.response.Document;
import ru.films.model.response.KinopoiskApiResponse;
import ru.films.repository.FilmRepository;
import ru.films.utils.mapper.FilmMapper;

import java.util.List;
import java.util.Map;
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
    private final KinopoiskApiClient kinopoiskApiClient;
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;
    private final CountryService countryService;
    private final GenreService genreService;

    public void addFilms() {
        KinopoiskApiResponse popularFilms = kinopoiskApiClient.getFilms(250, new String[]{"popular-films"});
        List<Document> documents = cleanDocuments(popularFilms.getDocs());
        List<Film> films = filmMapper.documentListToFilmList(documents);
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
            setCountry(newFilms);
            setGenre(newFilms);
            filmRepository.saveAll(newFilms);
        }
    }

    private void setCountry(List<Film> films) {
        Set<String> allCountryNames = films.stream()
                .flatMap(film -> film.getCountries().stream())
                .map(Country::getName)
                .collect(Collectors.toSet());
        Map<String, Country> countryMap = countryService.saveNotExistsCountry(allCountryNames);
        films.forEach(film -> {
            Set<Country> updatedCountries = film.getCountries().stream()
                    .map(country -> countryMap.get(country.getName()))
                    .collect(Collectors.toSet());
            film.setCountries(updatedCountries);
        });
    }

    private void setGenre(List<Film> films) {
        Set<String> allGenreNames = films.stream()
                .flatMap(film -> film.getGenres().stream())
                .map(Genre::getName)
                .collect(Collectors.toSet());
        Map<String, Genre> genreMap = genreService.saveNotExistsGenre(allGenreNames);
        films.forEach(film -> {
            Set<Genre> updatedGenres = film.getGenres().stream()
                    .map(genre -> genreMap.get(genre.getName()))
                    .collect(Collectors.toSet());
            film.setGenres(updatedGenres);
        });
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
