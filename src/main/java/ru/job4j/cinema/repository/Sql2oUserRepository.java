package ru.job4j.cinema.repository;

import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.User;

import java.util.Optional;

/**
 * Класс-репозиторий для работы с пользователями {@link User} в БД
 *
 * @author Artem Chernikov
 * @version 1.1
 * @since 26.02.2023
 */
@ThreadSafe
@Slf4j
@Repository
public class Sql2oUserRepository implements UserRepository {
    /**
     * Поле экземпляр {@link Sql2o} для работы с БД
     */
    private final Sql2o sql2o;

    public Sql2oUserRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    /**
     * Метод используется для записи пользователя {@link User} в БД
     *
     * @param user - пользователь
     * @return - возвращает пользователя {@link User} обернутого в {@link Optional}
     */
    @Override
    public Optional<User> save(User user) {
        try (var connection = sql2o.open()) {
            var sql = """
                    INSERT INTO users(full_name, email, password, role_id) VALUES (:fullName, :email, :password, :roleId)
                    """;
            var query = connection.createQuery(sql, true)
                    .addParameter("fullName", user.getFullName())
                    .addParameter("email", user.getEmail())
                    .addParameter("password", user.getPassword())
                    .addParameter("roleId", user.getRoleId());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            user.setId(generatedId);
            return Optional.of(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    /**
     * Метод используется для поиска пользователя {@link User} в БД по email и паролю
     *
     * @param email    - email
     * @param password - пароль
     * @return - возвращает пользователя {@link User} обернутого в {@link Optional}
     */
    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM users WHERE email = :email AND password = :password");
            query.addParameter("email", email);
            query.addParameter("password", password);
            var user = query.setColumnMappings(User.COLUMN_MAPPING).executeAndFetchFirst(User.class);
            return Optional.ofNullable(user);
        }
    }

    /**
     * Метод используется для поиска пользователя {@link User} в БД по email
     *
     * @param email    - email
     * @return - возвращает пользователя {@link User} обернутого в {@link Optional}
     */
    @Override
    public Optional<User> findByEmail(String email) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM users WHERE email = :email");
            query.addParameter("email", email);
            var user = query.setColumnMappings(User.COLUMN_MAPPING).executeAndFetchFirst(User.class);
            return Optional.ofNullable(user);
        }
    }
}
