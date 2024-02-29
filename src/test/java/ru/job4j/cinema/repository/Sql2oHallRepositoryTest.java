package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.sql2o.Sql2o;
import ru.job4j.cinema.configuration.DataSourceConfiguration;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class Sql2oHallRepositoryTest {

    private static Sql2oHallRepository sql2oHallRepository;

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

        sql2oHallRepository = new Sql2oHallRepository(sql2o);
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