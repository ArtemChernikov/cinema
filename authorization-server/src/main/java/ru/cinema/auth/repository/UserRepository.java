package ru.cinema.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cinema.auth.model.User;

import java.util.Optional;

/**
 * @author Artem Chernikov
 * @version 1.1
 * @since 08.07.2024
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
