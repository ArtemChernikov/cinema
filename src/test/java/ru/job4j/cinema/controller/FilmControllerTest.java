package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.service.FilmService;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class FilmControllerTest {

    private FilmService filmService;

    private FilmController filmController;

    @BeforeEach
    public void initService() {
        filmService = mock(FilmService.class);
        filmController = new FilmController(filmService);
    }

    /**
     * Метод используется для проверки корректности работы контроллера по выводу фильмов в отображение
     */
    @Test
    public void whenRequestFilmsListPageThenGetPageWithFilms() {
        var film1 = new FilmDto("name1", "description1", 2022, "Fantastic", 13, 100, 1);
        var film2 = new FilmDto("name2", "description2", 2012, "Horror", 18, 200, 2);
        var film3 = new FilmDto("name3", "description3", 2011, "Adventure", 6, 120, 3);
        var expectedFilms = List.of(film1, film2, film3);
        when(filmService.getAllFilms()).thenReturn(expectedFilms);

        var model = new ConcurrentModel();
        var view = filmController.getFilms(model);
        var actualFilms = model.getAttribute("films");

        assertThat(view).isEqualTo("films/list");
        assertThat(actualFilms).isEqualTo(expectedFilms);
    }
}