package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.sql2o.Sql2o;
import ru.job4j.cinema.configuration.DataSourceConfiguration;
import ru.job4j.cinema.model.File;

import javax.sql.DataSource;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class Sql2oFileRepositoryTest {

    private static Sql2oFileRepository sql2oFileRepository;

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

        sql2oFileRepository = new Sql2oFileRepository(sql2o);
    }

    /**
     * Метод используется для проверки корректности поиска файла по id в БД (сравниваются файлы)
     */
    @Test
    public void whenFindFileByIdIsSuccess() {
        var expectedFile = new File("avatar_the_way_of_water", "src/main/resources/static/posters/avatar_the_way_of_water.jpeg");
        expectedFile.setId(1);
        var actualFile = sql2oFileRepository.findById(1);
        assertThat(actualFile).isPresent();
        assertThat(actualFile.get()).usingRecursiveComparison().isEqualTo(expectedFile);
    }

    /**
     * Метод используется для проверки корректности поиска файла по id в БД
     * (не находит файл, сравнивается пустое значение)
     */
    @Test
    public void whenFindFileByIdIsFailed() {
        var actual = sql2oFileRepository.findById(100);
        assertThat(actual).isEmpty();
    }
}