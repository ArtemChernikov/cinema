package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class Sql2oUserRepositoryTest {

    private final Sql2oUserRepository sql2oUserRepository;

    private final Sql2o sql2o;

    @Autowired
    Sql2oUserRepositoryTest(Sql2oUserRepository sql2oUserRepository, Sql2o sql2o) {
        this.sql2oUserRepository = sql2oUserRepository;
        this.sql2o = sql2o;
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