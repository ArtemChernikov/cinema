package ru.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cinema.model.Role;

import java.util.Optional;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 27.01.2024
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
