package ru.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cinema.model.User;

import java.util.Optional;

/**
 * @author Artem Chernikov
 * @version 1.1
 * @since 26.02.2023
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);
}
