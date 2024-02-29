package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.sql2o.Sql2o;
import ru.job4j.cinema.configuration.DataSourceConfiguration;

import javax.sql.DataSource;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class Sql2oGenreRepositoryTest {

    private static Sql2oGenreRepository sql2oGenreRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        Properties properties = new Properties();
        try (var inputStream = Sql2oFileRepository.class.getClassLoader().getResourceAsStream("application-test.properties")) {
            properties.load(inputStream);
        }
        String url = properties.getProperty("spring.datasource.url");
        String username = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");

        DataSourceConfiguration configuration = new DataSourceConfiguration();
        DataSource dataSource = configuration.connectionPool(url, username, password);
        Sql2o sql2o = configuration.databaseClient(dataSource);

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