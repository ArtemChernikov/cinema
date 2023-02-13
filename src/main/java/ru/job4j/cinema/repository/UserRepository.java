package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.User;

import java.util.Optional;

/**
 * Общий интерфейс для всех репозиториев с пользователями {@link User}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
public interface UserRepository {
    Optional<User> save(User user);

    Optional<User> findByEmailAndPassword(String email, String password);
}
