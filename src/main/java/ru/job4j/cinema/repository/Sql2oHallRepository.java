package ru.job4j.cinema.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Hall;

import java.util.Collection;
import java.util.Optional;

/**
 * Класс-репозиторий для работы с кинозалами {@link Hall} в БД
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
@ThreadSafe
@Repository
public class Sql2oHallRepository implements HallRepository {
    /**
     * Поле экземпляр {@link Sql2o} для работы с БД
     */
    private final Sql2o sql2o;

    public Sql2oHallRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    /**
     * Метод используется для поиска кинозала {@link Hall} по id в БД
     *
     * @param id - id кинозала
     * @return - возвращает кинозал {@link Hall} обернутый в {@link Optional}
     */
    @Override
    public Optional<Hall> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM halls WHERE id = :id");
            query.addParameter("id", id);
            var hall = query.setColumnMappings(Hall.COLUMN_MAPPING).executeAndFetchFirst(Hall.class);
            return Optional.ofNullable(hall);
        }
    }

    /**
     * Метод используется для поиска всех кинозалов {@link Hall} в БД
     *
     * @return - возвращает коллекцию с кинозалами
     */
    @Override
    public Collection<Hall> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM halls");
            return query.setColumnMappings(Hall.COLUMN_MAPPING).executeAndFetch(Hall.class);
        }
    }
}
