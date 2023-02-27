package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DataSourceConfiguration;

import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oGenreRepositoryTest {

    private static Sql2oGenreRepository sql2oGenreRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oFileRepository.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DataSourceConfiguration();
        var connection = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(connection);

        sql2oGenreRepository = new Sql2oGenreRepository(sql2o);
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