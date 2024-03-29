package ru.job4j.cinema.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class Sql2oFilmSessionRepositoryTest {

    private final Sql2oFilmSessionRepository sql2oFilmSessionRepository;

    @Autowired
    Sql2oFilmSessionRepositoryTest(Sql2oFilmSessionRepository sql2oFilmSessionRepository) {
        this.sql2oFilmSessionRepository = sql2oFilmSessionRepository;
    }

    /**
     * Метод используется для проверки корректности поиска киносеанса по id в БД
     * (находит киносеанс и сравниваются поля)
     */
    @Test
    public void whenFindFilmSessionByIdIsSuccess() {
        var actualFilmSession = sql2oFilmSessionRepository.findById(1);
        assertThat(actualFilmSession).isPresent();
        assertThat(actualFilmSession.get().getId()).isEqualTo(1);
        assertThat(actualFilmSession.get().getFilmId()).isEqualTo(1);
        assertThat(actualFilmSession.get().getHallId()).isEqualTo(1);
        assertThat(actualFilmSession.get().getStartTime()).isEqualTo("2023-02-09T09:00");
        assertThat(actualFilmSession.get().getEndTime()).isEqualTo("2023-02-09T12:30");
        assertThat(actualFilmSession.get().getPrice()).isEqualTo(300);
    }

    /**
     * Метод используется для проверки корректности поиска киносеанса по id в БД
     * (не находит киносеанс, сравнивается пустое значение)
     */
    @Test
    public void whenFindFilmSessionByIdIsFiled() {
        var actual = sql2oFilmSessionRepository.findById(100);
        assertThat(actual).isEmpty();
    }

    /**
     * Метод используется для проверки корректности поиска всех киносеансов из БД
     * (сравниваются коллекции киносеансов)
     */
    @Test
    public void whenFindAllFilmsSessionsIsSuccess() {
        var expectedFilms = List.of(
                sql2oFilmSessionRepository.findById(1).get(), sql2oFilmSessionRepository.findById(2).get(),
                sql2oFilmSessionRepository.findById(3).get(), sql2oFilmSessionRepository.findById(4).get(),
                sql2oFilmSessionRepository.findById(5).get(), sql2oFilmSessionRepository.findById(6).get(),
                sql2oFilmSessionRepository.findById(7).get(), sql2oFilmSessionRepository.findById(8).get(),
                sql2oFilmSessionRepository.findById(9).get(), sql2oFilmSessionRepository.findById(10).get(),
                sql2oFilmSessionRepository.findById(11).get(), sql2oFilmSessionRepository.findById(12).get(),
                sql2oFilmSessionRepository.findById(13).get(), sql2oFilmSessionRepository.findById(14).get(),
                sql2oFilmSessionRepository.findById(15).get(), sql2oFilmSessionRepository.findById(16).get(),
                sql2oFilmSessionRepository.findById(17).get(), sql2oFilmSessionRepository.findById(18).get(),
                sql2oFilmSessionRepository.findById(19).get(), sql2oFilmSessionRepository.findById(20).get(),
                sql2oFilmSessionRepository.findById(21).get(), sql2oFilmSessionRepository.findById(22).get(),
                sql2oFilmSessionRepository.findById(23).get(), sql2oFilmSessionRepository.findById(24).get(),
                sql2oFilmSessionRepository.findById(25).get(), sql2oFilmSessionRepository.findById(26).get(),
                sql2oFilmSessionRepository.findById(27).get(), sql2oFilmSessionRepository.findById(28).get(),
                sql2oFilmSessionRepository.findById(29).get(), sql2oFilmSessionRepository.findById(30).get()
        );
        var actualFilmSessions = sql2oFilmSessionRepository.findAll();
        assertThat(expectedFilms).usingRecursiveComparison().isEqualTo(actualFilmSessions);
    }
}