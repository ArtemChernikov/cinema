package ru.cinema.service;

import ru.cinema.model.User;

import java.util.Optional;

/**
 *
 * @author Artem Chernikov
 * @version 1.1
 * @since 26.02.2023
 */
public interface UserService {
    Optional<User> save(User user);

    Optional<User> getById(long id);

    Optional<User> getByUsername(String username);

    Optional<User> getByUsernameAndPassword(String username, String password);
}
