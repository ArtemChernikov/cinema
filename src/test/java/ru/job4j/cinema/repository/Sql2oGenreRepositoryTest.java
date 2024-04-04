package ru.job4j.cinema.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.cinema.repository.sql2o.Sql2oGenreRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class Sql2oGenreRepositoryTest {

    private final Sql2oGenreRepository sql2oGenreRepository;

    @Autowired
    Sql2oGenreRepositoryTest(Sql2oGenreRepository sql2oGenreRepository) {
        this.sql2oGenreRepository = sql2oGenreRepository;
    }

    /**
     * Метод используется для проверки корректности поиска жанра по id в БД
     * (находит жанр и сравниваются поля)
     */
    @Test
    public void whenFindGenreByIdIsSuccess() {
        var actualGenre1 = sql2oGenreRepository.findById(1);
        var actualGenre2 = sql2oGenreRepository.findById(2);
        var actualGenre3 = sql2oGenreRepository.findById(3);
        assertThat(actualGenre1).isPresent();
        assertThat(actualGenre1.get().getId()).isEqualTo(1);
        assertThat(actualGenre1.get().getName()).isEqualTo("Фантастика");

        assertThat(actualGenre2).isPresent();
        assertThat(actualGenre2.get().getId()).isEqualTo(2);
        assertThat(actualGenre2.get().getName()).isEqualTo("Боевик");

        assertThat(actualGenre3).isPresent();
        assertThat(actualGenre3.get().getId()).isEqualTo(3);
        assertThat(actualGenre3.get().getName()).isEqualTo("Детектив");

    }

    /**
     * Метод используется для проверки корректности поиска жанра по id в БД
     * (не находит жанр, сравнивается пустое значение)
     */
    @Test
    public void whenFindGenreByIdIsFiled() {
        var actual = sql2oGenreRepository.findById(100);
        assertThat(actual).isEmpty();
    }

}