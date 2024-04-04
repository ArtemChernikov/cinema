package ru.job4j.cinema.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.cinema.repository.sql2o.Sql2oHallRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class Sql2oHallRepositoryTest {

    private final Sql2oHallRepository sql2oHallRepository;

    @Autowired
    Sql2oHallRepositoryTest(Sql2oHallRepository sql2oHallRepository) {
        this.sql2oHallRepository = sql2oHallRepository;
    }

    /**
     * Метод используется для проверки корректности поиска кинозала по id в БД
     * (находит кинозал и сравниваются поля)
     */
    @Test
    public void whenFindHallByIdIsSuccess() {
        var actualHall = sql2oHallRepository.findById(1);
        assertThat(actualHall).isPresent();
        assertThat(actualHall.get().getId()).isEqualTo(1);
        assertThat(actualHall.get().getName()).isEqualTo("1 зал");
        assertThat(actualHall.get().getRowCount()).isEqualTo(12);
        assertThat(actualHall.get().getPlaceCount()).isEqualTo(16);
        assertThat(actualHall.get().getDescription()).isEqualTo("Большой, комфортный зал на 190 человек.");
        assertThat(actualHall.get().getFileId()).isEqualTo(11);
    }

    /**
     * Метод используется для проверки корректности поиска кинозала по id в БД
     * (не находит кинозал, сравнивается пустое значение)
     */
    @Test
    public void whenFindHallByIdIsFiled() {
        var actual = sql2oHallRepository.findById(100);
        assertThat(actual).isEmpty();
    }

    /**
     * Метод используется для проверки корректности поиска всех кинозалов из БД
     * (сравниваются коллекции кинозалов)
     */
    @Test
    public void whenFindAllHallsIsSuccess() {
        var expectedHalls = List.of(
                sql2oHallRepository.findById(1).get(),
                sql2oHallRepository.findById(2).get(),
                sql2oHallRepository.findById(3).get()
        );
        var actualHalls = sql2oHallRepository.findAll();
        assertThat(expectedHalls).usingRecursiveComparison().isEqualTo(actualHalls);
    }
}