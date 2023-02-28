package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.service.HallService;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HallControllerTest {

    private HallService hallService;

    private HallController hallController;

    @BeforeEach
    public void initService() {
        hallService = mock(HallService.class);
        hallController = new HallController(hallService);
    }

    /**
     * Метод используется для проверки корректности работы контроллера по выводу кинозалов в отображение
     */
    @Test
    public void whenRequestHallsListPageThenGetPageWithHalls() {
        var hall1 = new Hall("name1", 12, 12, "description1", 1);
        var hall2 = new Hall("name2", 2, 14, "description2", 2);
        var hall3 = new Hall("name3", 10, 12, "description3", 3);
        var expectedHalls = List.of(hall1, hall2, hall3);
        when(hallService.getAllHalls()).thenReturn(expectedHalls);

        var model = new ConcurrentModel();
        var view = hallController.getHalls(model);
        var actualHalls = model.getAttribute("halls");

        assertThat(view).isEqualTo("halls/list");
        assertThat(actualHalls).isEqualTo(expectedHalls);
    }
}