package ru.job4j.cinema.controller;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class IndexControllerTest {
    /**
     * Метод используется для проверки корректности работы контроллера по выводу главной страницы в отображение
     */
    @Test
    public void whenRequestHomePageThenGetHomePage() {
        IndexController indexController = new IndexController();
        var view = indexController.getIndex();
        assertThat(view).isEqualTo("index");
    }
}