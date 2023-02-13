package ru.job4j.cinema.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.Optional;

/**
 * Класс-репозиторий для работы с фильмами {@link Film} в БД
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
@ThreadSafe
@Repository
public class Sql2oFilmRepository implements FilmRepository {
    /**
     * Поле экземпляр {@link Sql2o} для работы с БД
     */
    private final Sql2o sql2o;

    public Sql2oFilmRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    /**
     * Метод используется для поиска фильма {@link Film} по id в БД
     *
     * @param id - id фильма
     * @return - возвращает фильм {@link Film} обернутый в {@link Optional}
     */
    @Override
    public Optional<Film> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM films WHERE id = :id");
            query.addParameter("id", id);
            var film = query.setColumnMappings(Film.COLUMN_MAPPING).executeAndFetchFirst(Film.class);
            return Optional.ofNullable(film);
        }
    }

    /**
     * Метод используется для поиска всех фильмов {@link Film} в БД
     *
     * @return - возвращает коллекцию с фильмами
     */
    @Override
    public Collection<Film> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM films");
            return query.setColumnMappings(Film.COLUMN_MAPPING).executeAndFetch(Film.class);
        }
    }
}
