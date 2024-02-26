package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Role;

import java.util.Optional;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 26.02.2024
 */
@Repository
public class Sql2oRoleRepository implements RoleRepository {
    private final Sql2o sql2o;

    public Sql2oRoleRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Role> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM roles WHERE id = :id");
            query.addParameter("id", id);
            var role = query.executeAndFetchFirst(Role.class);
            return Optional.ofNullable(role);
        }
    }

    @Override
    public Optional<Role> findByName(String name) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM roles WHERE name = :name");
            query.addParameter("name", name);
            var role = query.executeAndFetchFirst(Role.class);
            return Optional.ofNullable(role);
        }
    }
}
