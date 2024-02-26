package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findById(int id);

    Optional<Role> findByName(String name);
}
