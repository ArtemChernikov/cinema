package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.service.FilmSessionService;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilmSessionControllerTest {

    private FilmSessionService filmSessionService;

    private FilmSessionController filmSessionController;

    @BeforeEach
    public void initService() {
        filmSessionService = mock(FilmSessionService.class);
        filmSessionController = new FilmSessionController(filmSessionService);
    }

    /**
     * Метод используется для проверки корректности работы контроллера по выводу киносеансов в отображение
     */
    @Test
    public void whenRequestFilmSessionsListPageThenGetPageWithFilmSessions() {
        var filmSession1 = new FilmSessionDto(1, "film1", "hall1", 1, LocalDateTime.now(), LocalDateTime.now().plusHours(2), 300);
        var filmSession2 = new FilmSessionDto(2, "film2", "hall2", 2, LocalDateTime.now(), LocalDateTime.now().plusHours(2), 400);
        var filmSession3 = new FilmSessionDto(3, "film3", "hall3", 3, LocalDateTime.now(), LocalDateTime.now().plusHours(2), 500);
        var expectedFilmSessions = List.of(filmSession1, filmSession2, filmSession3);
        when(filmSessionService.getAllFilmSessions()).thenReturn(expectedFilmSessions);

        var model = new ConcurrentModel();
        var view = filmSessionController.getFilmSessions(model);
        var actualFilmSessions = model.getAttribute("filmSessions");

        assertThat(view).isEqualTo("filmSessions/list");
        assertThat(actualFilmSessions).isEqualTo(expectedFilmSessions);
    }

}