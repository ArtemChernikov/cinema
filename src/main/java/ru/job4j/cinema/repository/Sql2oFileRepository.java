package ru.job4j.cinema.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.File;

import java.util.Optional;

/**
 * Класс-репозиторий для работы с файлами {@link File} в БД
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
@ThreadSafe
@Repository
public class Sql2oFileRepository implements FileRepository {
    /**
     * Поле экземпляр {@link Sql2o} для работы с БД
     */
    private final Sql2o sql2o;

    public Sql2oFileRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    /**
     * Метод используется для поиска файла {@link File} по id в БД
     *
     * @param id - id файла
     * @return - возвращает файл {@link File} обурнутый в {@link Optional}
     */
    @Override
    public Optional<File> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM files WHERE id = :id");
            query.addParameter("id", id);
            var file = query.executeAndFetchFirst(File.class);
            return Optional.ofNullable(file);
        }
    }
}
