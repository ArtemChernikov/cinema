package ru.films.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.films.client.KinopoiskClient;
import ru.films.exception.FilmNotFoundException;
import ru.films.model.Country;
import ru.films.model.Film;
import ru.films.model.Genre;
import ru.films.model.dto.FilmDto;
import ru.films.model.response.Document;
import ru.films.model.response.Response;
import ru.films.repository.BackdropRepository;
import ru.films.repository.CountryRepository;
import ru.films.repository.FilmRepository;
import ru.films.repository.GenreRepository;
import ru.films.repository.PosterRepository;
import ru.films.repository.RatingRepository;
import ru.films.utils.mapper.FilmMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private final BackdropRepository backdropRepository;
    private final PosterRepository posterRepository;
    private final RatingRepository ratingRepository;
    private final CountryRepository countryRepository;
    private final GenreRepository genreRepository;
    private final FilmMapper filmMapper;
    private final ObjectMapper objectMapper;

    public void addFilms() {
        clearData();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Document> documents = objectMapper
                .convertValue(kinopoiskClient.getPopularFilms().getBody(), Response.class).getDocs();
        List<Film> films = filmMapper.documentListToFilmList(documents);
        setCountry(films);
        setGenre(films);
        filmRepository.saveAll(films);
    }

    public List<FilmDto> getAll() {
        return filmMapper.filmListToFilmDtoList(filmRepository.findAll());
    }

    @Override
    public FilmDto getById(Long id) {
        return filmMapper.filmToFilmDto(filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException(FILM_NOT_FOUND)));
    }

    private void setCountry(List<Film> films) {
        Set<String> allCountryNames = films.stream()
                .flatMap(film -> film.getCountries().stream())
                .map(Country::getName)
                .collect(Collectors.toSet());

        List<Country> existingCountries = countryRepository.findByNameIn(new ArrayList<>(allCountryNames));
        Set<String> existingCountryNames = existingCountries.stream()
                .map(Country::getName)
                .collect(Collectors.toSet());

        List<Country> newCountries = allCountryNames.stream()
                .filter(name -> !existingCountryNames.contains(name))
                .map(name -> Country.builder().name(name).build())
                .toList();
        newCountries = countryRepository.saveAll(newCountries);


        Map<String, Country> countryMap = Stream.concat(existingCountries.stream(), newCountries.stream())
                .collect(Collectors.toMap(Country::getName, Function.identity()));

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

        List<Genre> existingGenres = genreRepository.findByNameIn(new ArrayList<>(allGenreNames));

        Set<String> existingGenreNames = existingGenres.stream()
                .map(Genre::getName)
                .collect(Collectors.toSet());

        List<Genre> newGenres = allGenreNames.stream()
                .filter(name -> !existingGenreNames.contains(name))
                .map(name -> Genre.builder().name(name).build())
                .toList();
        newGenres = genreRepository.saveAll(newGenres);


        Map<String, Genre> genreMap = Stream.concat(existingGenres.stream(), newGenres.stream())
                .collect(Collectors.toMap(Genre::getName, Function.identity()));

        films.forEach(film -> {
            Set<Genre> updatedGenres = film.getGenres().stream()
                    .map(genre -> genreMap.get(genre.getName()))
                    .collect(Collectors.toSet());
            film.setGenres(updatedGenres);
        });
    }

    private void clearData() {
        filmRepository.deleteAllInBatch();
        backdropRepository.deleteAllInBatch();
        posterRepository.deleteAllInBatch();
        ratingRepository.deleteAllInBatch();
    }
}
