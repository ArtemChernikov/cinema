package ru.cinema.exception.message;

import lombok.experimental.UtilityClass;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 18.05.2024
 */
@UtilityClass
public class UserExceptionMessage {
    public static final String USER_NOT_FOUND = "Пользователь не найден.";
    public static final String USERNAME_ALREADY_EXISTS = "Пользователь с данным логином уже существует.";
}
