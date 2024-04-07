package ru.job4j.cinema.service;

import ru.job4j.cinema.model.User;

import java.util.Optional;

/**
 * Общий интерфейс для всех классов-сервисов по работе с пользователями {@link User}
 *
 * @author Artem Chernikov
 * @version 1.1
 * @since 26.02.2023
 */
public interface UserService {
    Optional<User> save(User user);

    Optional<User> getById(int id);

    Optional<User> getByUsername(String username);

    Optional<User> getByUsernameAndPassword(String username, String password);
}
