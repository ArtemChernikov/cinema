package ru.job4j.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.cinema.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
