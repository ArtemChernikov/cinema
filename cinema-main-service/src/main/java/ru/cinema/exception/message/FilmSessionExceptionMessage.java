package ru.cinema.exception.message;

import lombok.experimental.UtilityClass;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 17.05.2024
 */
@UtilityClass
public class FilmSessionExceptionMessage {
    public static final String END_TIME_BEFORE_START_TIME =
            "Дата начала сеанса не может быть позже даты конца сеанса.";
    public static final String START_END_BEFORE_CURRENT =
            "Дата начала или конца сеанса не может быть раньше текущей даты.";
    public static final String FILM_SESSION_NOT_FOUND =
            "Киносеанс не найден.";
}
