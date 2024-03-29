package ru.job4j.cinema.model;

import java.util.*;

/**
 * Класс описывает модель пользователя
 *
 * @author Artem Chernikov
 * @version 1.1
 * @since 26.02.2023
 */
public class User {
    /**
     * Поле используется для маппинга модели где ключи это названия
     * столбцов в БД, а значения это названия полей модели
     */
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "full_name", "fullName",
            "email", "email",
            "password", "password",
            "role_id", "roleId"
    );

    private int id;

    private String fullName;

    private String email;

    private String password;

    private int roleId;

    public User(String fullName, String email, String password, int roleId) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
