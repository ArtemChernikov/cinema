package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.sql2o.Sql2o;
import ru.job4j.cinema.configuration.DataSourceConfiguration;
import ru.job4j.cinema.model.User;

import javax.sql.DataSource;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class Sql2oUserRepositoryTest {

    private static Sql2oUserRepository sql2oUserRepository;

    private static Sql2o sql2o;

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
        sql2o = configuration.databaseClient(dataSource);

        sql2oUserRepository = new Sql2oUserRepository(sql2o);
    }

    @AfterEach
    public void clearUsers() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM users");
            query.executeUpdate();
        }
    }

    /**
     * Метод используется для проверки корректности сохранения пользователя в БД
     * и поиска пользователя в БД по имени и паролю
     */
    @Test
    public void whenSaveAndThenGetUserIsSuccess() {
        var expectedUser = new User("Artem Chernikov", "qwerty@yandex.ru", "12345", 1);
        var savedUser = sql2oUserRepository.save(expectedUser);
        var findUser = sql2oUserRepository.findByEmailAndPassword("qwerty@yandex.ru", "12345");
        assertThat(savedUser).isPresent();
        assertThat(findUser).isPresent();
        assertThat(savedUser.get()).usingRecursiveComparison().isEqualTo(expectedUser);
        assertThat(findUser.get()).usingRecursiveComparison().isEqualTo(expectedUser);
    }

    /**
     * Метод используется для проверки корректности сохранения пользователя в БД
     * и поиска пользователя в БД по имени и паролю
     */
    @Test
    public void whenSaveIsSuccessAndGetUserIsFiled() {
        var expectedUser = new User("Artem Chernikov", "qwerty@yandex.ru", "12345", 1);
        var savedUser = sql2oUserRepository.save(expectedUser);
        var findUser = sql2oUserRepository.findByEmailAndPassword("123@yandex.ru", "12345");
        assertThat(savedUser).isPresent();
        assertThat(findUser).isEmpty();
        assertThat(savedUser.get()).usingRecursiveComparison().isEqualTo(expectedUser);
    }
}