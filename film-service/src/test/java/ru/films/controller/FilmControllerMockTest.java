package ru.films.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.films.exception.FilmNotFoundException;
import ru.films.model.dto.BackdropDto;
import ru.films.model.dto.CollectionDto;
import ru.films.model.dto.FilmDto;
import ru.films.model.dto.PosterDto;
import ru.films.model.dto.RatingDto;
import ru.films.service.CollectionService;
import ru.films.service.FilmService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.films.exception.message.FilmExceptionMessage.FILM_NOT_FOUND;

@WebMvcTest(FilmController.class)
class FilmControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService filmService;

    @MockBean
    private CollectionService collectionService;

    private FilmDto filmDto1;

    private FilmDto filmDto2;

    private CollectionDto collectionDto1;

    private CollectionDto collectionDto2;

    @BeforeEach
    void init() {
        RatingDto rating1 = new RatingDto(7.8, 8.2, 6.5, 5.9);
        PosterDto poster1 = new PosterDto("poster1_url", "poster1_preview_url");
        BackdropDto backdrop1 = new BackdropDto("backdrop1_url", "backdrop1_preview_url");

        RatingDto rating2 = new RatingDto(6.5, 7.0, 5.0, 4.5);
        PosterDto poster2 = new PosterDto("poster2_url", "poster2_preview_url");
        BackdropDto backdrop2 = new BackdropDto("backdrop2_url", "backdrop2_preview_url");

        Set<String> genres1 = new HashSet<>(Arrays.asList("Action", "Drama"));
        Set<String> countries1 = new HashSet<>(Arrays.asList("USA", "UK"));

        Set<String> genres2 = new HashSet<>(Arrays.asList("Comedy", "Romance"));
        Set<String> countries2 = new HashSet<>(Arrays.asList("France", "Germany"));

        filmDto1 = new FilmDto(1L, "Film 1", "Alt Film 1", "Movie", 2020,
                "Description of Film 1", "Short description of Film 1", rating1, 120,
                16, poster1, backdrop1, genres1, countries1
        );
        filmDto2 = new FilmDto(2L, "Film 2", "Alt Film 2", "Series", 2018,
                "Description of Film 2", "Short description of Film 2", rating2, 90,
                12, poster2, backdrop2, genres2, countries2
        );

        collectionDto1 = new CollectionDto("Action Movies", "action_movies");
        collectionDto2 = new CollectionDto("Comedy Films", "comedy_films");
    }

    @Test
    @DisplayName("Тест на добавление фильмов")
    @SneakyThrows
    void whenAddPopularFilms() {
        mockMvc.perform(post("/films/add-popular-films"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Тест на получение всех фильмов")
    @SneakyThrows
    void whenGetAllFilmsThenReturnTwoFilms() {
        when(filmService.getAll()).thenReturn(List.of(filmDto1, filmDto2));

        mockMvc.perform(get("/films")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("Тест на получение всех фильмов - пустой список")
    @SneakyThrows
    void whenGetAllFilmsThenReturnEmptyList() {
        mockMvc.perform(get("/films")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("Тест на получение фильма по id")
    @SneakyThrows
    void whenGetFilmById() {
        when(filmService.getById(filmDto1.getId())).thenReturn(filmDto1);

        mockMvc.perform(get("/films/{id}", filmDto1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(filmDto1.getId()))
                .andExpect(jsonPath("$.name").value(filmDto1.getName()))
                .andExpect(jsonPath("$.alternativeName").value(filmDto1.getAlternativeName()))
                .andExpect(jsonPath("$.type").value(filmDto1.getType()))
                .andExpect(jsonPath("$.year").value(filmDto1.getYear()))
                .andExpect(jsonPath("$.description").value(filmDto1.getDescription()))
                .andExpect(jsonPath("$.shortDescription").value(filmDto1.getShortDescription()))
                .andExpect(jsonPath("$.movieLength").value(filmDto1.getMovieLength()))
                .andExpect(jsonPath("$.ageRating").value(filmDto1.getAgeRating()))
                .andExpect(jsonPath("$.rating.kpRate").value(filmDto1.getRating().getKpRate()))
                .andExpect(jsonPath("$.rating.imdbRate").value(filmDto1.getRating().getImdbRate()))
                .andExpect(jsonPath("$.rating.filmCriticsRate").value(filmDto1.getRating().getFilmCriticsRate()))
                .andExpect(jsonPath("$.rating.russianFilmCriticsRate").value(filmDto1.getRating().getRussianFilmCriticsRate()))
                .andExpect(jsonPath("$.poster.url").value(filmDto1.getPoster().getUrl()))
                .andExpect(jsonPath("$.poster.previewUrl").value(filmDto1.getPoster().getPreviewUrl()))
                .andExpect(jsonPath("$.backdrop.url").value(filmDto1.getBackdrop().getUrl()))
                .andExpect(jsonPath("$.backdrop.previewUrl").value(filmDto1.getBackdrop().getPreviewUrl()))
                .andExpect(jsonPath("$.genres", hasSize(filmDto1.getGenres().size())))
                .andExpect(jsonPath("$.genres").isArray())
                .andExpect(jsonPath("$.countries", hasSize(filmDto1.getCountries().size())))
                .andExpect(jsonPath("$.countries").isArray());
    }

    @Test
    @DisplayName("Тест на получение фильма по id - исключение FilmNotFoundException")
    @SneakyThrows
    void whenGetFilmByIdThenThrowsException() {
        when(filmService.getById(filmDto1.getId())).thenThrow(new FilmNotFoundException(FILM_NOT_FOUND));

        mockMvc.perform(get("/films/{id}", filmDto1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.reason").value("Ресурс не найден."))
                .andExpect(jsonPath("$.message").value(FILM_NOT_FOUND));
    }

    @Test
    @DisplayName("Тест на получение всех названий коллекций фильмов")
    @SneakyThrows
    void whenGetAllCollectionsThenReturnTwoCollections() {
        when(collectionService.getAll()).thenReturn(List.of(collectionDto1, collectionDto2));

        mockMvc.perform(get("/films/collections")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("Тест на получение всех названий коллекций фильмов - пустой список")
    @SneakyThrows
    void henGetAllCollectionsThenReturnEmptyList() {
        mockMvc.perform(get("/films/collections")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

}