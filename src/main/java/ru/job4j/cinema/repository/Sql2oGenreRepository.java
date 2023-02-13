package ru.job4j.cinema.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Genre;

import java.util.Optional;

/**
 * Класс-репозиторий для работы с жанрами фильмов {@link Genre} в БД
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
@ThreadSafe
@Repository
public class Sql2oGenreRepository implements GenreRepository {
    /**
     * Поле экземпляр {@link Sql2o} для работы с БД
     */
    private final Sql2o sql2o;

    public Sql2oGenreRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    /**
     * Метод используется для поиска жанра {@link Genre} по id в БД
     *
     * @param id - id жанра
     * @return - возвращает жанр {@link Genre} обернутый в {@link Optional}
     */
    @Override
    public Optional<Genre> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM genres WHERE id = :id");
            query.addParameter("id", id);
            var genre = query.executeAndFetchFirst(Genre.class);
            return Optional.ofNullable(genre);
        }
    }
}
