package ru.films.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.films.model.Backdrop;
import ru.films.model.Collection;
import ru.films.model.Country;
import ru.films.model.Film;
import ru.films.model.Genre;
import ru.films.model.Poster;
import ru.films.model.Rating;
import ru.films.model.dto.BackdropDto;
import ru.films.model.dto.CollectionDto;
import ru.films.model.dto.FilmDto;
import ru.films.model.dto.PosterDto;
import ru.films.model.dto.RatingDto;
import ru.films.repository.CollectionRepository;
import ru.films.repository.CountryRepository;
import ru.films.repository.FilmRepository;
import ru.films.repository.GenreRepository;
import ru.films.utils.mapper.BackdropMapper;
import ru.films.utils.mapper.CollectionMapper;
import ru.films.utils.mapper.PosterMapper;
import ru.films.utils.mapper.RatingMapper;
import utils.IntegrationTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.films.exception.message.FilmExceptionMessage.FILM_NOT_FOUND;

@AutoConfigureMockMvc(addFilters = false)
class FilmControllerIntegrationTest extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private RatingMapper ratingMapper;

    @Autowired
    private PosterMapper posterMapper;

    @Autowired
    private BackdropMapper backdropMapper;

    @Autowired
    private CollectionMapper collectionMapper;

    private FilmDto filmDto1;

    private FilmDto filmDto2;

    private CollectionDto collectionDto1;

    private CollectionDto collectionDto2;

    @BeforeEach
    void init() {
        Country country1 = countryRepository.save(Country.builder().name("USA").build());
        Country country2 = countryRepository.save(Country.builder().name("UK").build());
        Country country3 = countryRepository.save(Country.builder().name("France").build());
        Country country4 = countryRepository.save(Country.builder().name("Germany").build());

        Genre genre1 = genreRepository.save(Genre.builder().name("Action").build());
        Genre genre2 = genreRepository.save(Genre.builder().name("Drama").build());
        Genre genre3 = genreRepository.save(Genre.builder().name("Comedy").build());
        Genre genre4 = genreRepository.save(Genre.builder().name("Romance").build());

        Rating rating1 = Rating.builder().kpRate(7.8).imdbRate(8.2).filmCriticsRate(6.5).russianFilmCriticsRate(5.9).build();
        Rating rating2 = Rating.builder().kpRate(6.5).imdbRate(7.0).filmCriticsRate(5.0).russianFilmCriticsRate(4.5).build();

        Poster poster1 = Poster.builder().url("poster1_url").previewUrl("poster1_preview_url").build();
        Poster poster2 = Poster.builder().url("poster2_url").previewUrl("poster2_preview_url").build();

        Backdrop backdrop1 = Backdrop.builder().url("backdrop1_url").previewUrl("backdrop1_preview_url").build();
        Backdrop backdrop2 = Backdrop.builder().url("backdrop2_url").previewUrl("backdrop2_preview_url").build();

        Film film1 = filmRepository.save(Film.builder()
                .id(1L)
                .name("Film 1")
                .alternativeName("Alt Film 1")
                .type("Movie")
                .year("2020")
                .description("Description of Film 1")
                .shortDescription("Short description of Film 1")
                .rating(rating1)
                .movieLength(120)
                .ageRating(16)
                .poster(poster1)
                .backdrop(backdrop1)
                .genres(Set.of(genre1, genre2))
                .countries(Set.of(country1, country2))
                .build());
        Film film2 = filmRepository.save(Film.builder()
                .id(2L)
                .name("Film 2")
                .alternativeName("Alt Film 2")
                .type("Series")
                .year("2018")
                .description("Description of Film 2")
                .shortDescription("Short description of Film 2")
                .rating(rating2)
                .movieLength(90)
                .ageRating(12)
                .poster(poster2)
                .backdrop(backdrop2)
                .genres(Set.of(genre3, genre4))
                .countries(Set.of(country3, country4))
                .build());

        Collection collection1 = collectionRepository.save(Collection.builder()
                .name("Action Movies")
                .searchName("action_movies")
                .build());

        Collection collection2 = collectionRepository.save(Collection.builder()
                .name("Comedy Films")
                .searchName("comedy_films")
                .build());

        RatingDto ratingDto1 = ratingMapper.ratingToRatingDto(rating1);
        PosterDto posterDto1 = posterMapper.posterToPosterDto(poster1);
        BackdropDto backdropDto1 = backdropMapper.backdropToBackdropDto(backdrop1);

        RatingDto ratingDto2 = ratingMapper.ratingToRatingDto(rating2);
        PosterDto posterDto2 = posterMapper.posterToPosterDto(poster2);
        BackdropDto backdropDto2 = backdropMapper.backdropToBackdropDto(backdrop2);

        Set<String> genres1 = new HashSet<>(Arrays.asList(genre1.getName(), genre2.getName()));
        Set<String> countries1 = new HashSet<>(Arrays.asList(country1.getName(), country2.getName()));

        Set<String> genres2 = new HashSet<>(Arrays.asList(genre3.getName(), genre4.getName()));
        Set<String> countries2 = new HashSet<>(Arrays.asList(country3.getName(), country4.getName()));

        filmDto1 = new FilmDto(film1.getId(), film1.getName(), film1.getAlternativeName(), film1.getType(), film1.getYear(),
                film1.getDescription(), film1.getShortDescription(), ratingDto1, film1.getMovieLength(),
                film1.getAgeRating(), posterDto1, backdropDto1, genres1, countries1
        );
        filmDto2 = new FilmDto(film2.getId(), film2.getName(), film2.getAlternativeName(), film2.getType(), film2.getYear(),
                film2.getDescription(), film2.getShortDescription(), ratingDto2, film2.getMovieLength(),
                film2.getAgeRating(), posterDto2, backdropDto2, genres2, countries2
        );

        collectionDto1 = collectionMapper.collectionToCollectionDto(collection1);
        collectionDto2 = collectionMapper.collectionToCollectionDto(collection2);
    }

    @AfterEach
    void clearDatabase() {
        filmRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
        countryRepository.deleteAllInBatch();
        collectionRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("Тест на получение всех фильмов")
    @SneakyThrows
    void whenGetAllFilmsThenReturnTwoFilms() {
        String actual = mockMvc.perform(get("/films")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(actual).isEqualTo(objectMapper.writeValueAsString(List.of(filmDto1, filmDto2)));
    }

    @Test
    @DisplayName("Тест на получение фильма по id")
    @SneakyThrows
    void whenGetFilmById() {
        String actual = mockMvc.perform(get("/films/{id}", filmDto1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(actual).isEqualTo(objectMapper.writeValueAsString(filmDto1));
    }

    @Test
    @DisplayName("Тест на получение фильма по id - исключение FilmNotFoundException")
    @SneakyThrows
    void whenGetFilmByIdThenThrowsException() {
        mockMvc.perform(get("/films/{id}", 999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.reason").value("Ресурс не найден."))
                .andExpect(jsonPath("$.message").value(FILM_NOT_FOUND));
    }

    @Test
    @DisplayName("Тест на получение всех названий коллекций фильмов")
    @SneakyThrows
    void whenGetAllCollectionsThenReturnTwoCollections() {
        String actual = mockMvc.perform(get("/films/collections")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(actual).isEqualTo(objectMapper.writeValueAsString(List.of(collectionDto1, collectionDto2)));
    }

}