package ru.job4j.cinema.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.cinema.repository.sql2o.Sql2oFilmRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class Sql2oFilmRepositoryTest {

    private final Sql2oFilmRepository sql2oFilmRepository;

    @Autowired
    Sql2oFilmRepositoryTest(Sql2oFilmRepository sql2oFilmRepository) {
        this.sql2oFilmRepository = sql2oFilmRepository;
    }

    /**
     * Метод используется для проверки корректности поиска фильма по id в БД
     * (находит фильм и сравниваются поля)
     */
    @Test
    public void whenFindFilmByIdIsSuccess() {
        var actualFilm = sql2oFilmRepository.findById(1);
        assertThat(actualFilm).isPresent();
        assertThat(actualFilm.get().getId()).isEqualTo(1);
        assertThat(actualFilm.get().getName()).isEqualTo("Аватар: Путь воды");
        assertThat(actualFilm.get().getDescription()).isEqualTo("После принятия образа аватара солдат Джейк Салли становится предводителем народа на'ви и берет на себя миссию по защите новых друзей от корыстных бизнесменов с Земли. Теперь ему есть за кого бороться — с Джейком его прекрасная возлюбленная Нейтири. Когда на Пандору возвращаются до зубов вооруженные земляне, Джейк готов дать им отпор.");
        assertThat(actualFilm.get().getYear()).isEqualTo(2022);
        assertThat(actualFilm.get().getGenreId()).isEqualTo(1);
        assertThat(actualFilm.get().getMinimalAge()).isEqualTo(13);
        assertThat(actualFilm.get().getDurationInMinutes()).isEqualTo(192);
        assertThat(actualFilm.get().getFileId()).isEqualTo(1);
    }

    /**
     * Метод используется для проверки корректности поиска фильма по id в БД
     * (не находит фильм, сравнивается пустое значение)
     */
    @Test
    public void whenFindFilmByIdIsFiled() {
        var actual = sql2oFilmRepository.findById(100);
        assertThat(actual).isEmpty();
    }

    /**
     * Метод используется для проверки корректности поиска всех фильмов из БД
     * (сравниваются коллекции фильмов)
     */
    @Test
    public void whenFindAllFilmsIsSuccess() {
        var expectedFilms = List.of(
                sql2oFilmRepository.findById(1).get(),
                sql2oFilmRepository.findById(2).get(),
                sql2oFilmRepository.findById(3).get(),
                sql2oFilmRepository.findById(4).get(),
                sql2oFilmRepository.findById(5).get(),
                sql2oFilmRepository.findById(6).get(),
                sql2oFilmRepository.findById(7).get(),
                sql2oFilmRepository.findById(8).get(),
                sql2oFilmRepository.findById(9).get(),
                sql2oFilmRepository.findById(10).get()
        );
        var actualFilms = sql2oFilmRepository.findAll();
        assertThat(expectedFilms).usingRecursiveComparison().isEqualTo(actualFilms);
    }
}