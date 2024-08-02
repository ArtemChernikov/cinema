package ru.cinema.exception.message;

import lombok.experimental.UtilityClass;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 17.05.2024
 */
@UtilityClass
public class FilmSessionExceptionMessage {
    public static final String END_TIME_BEFORE_START_TIME = "Дата начала сеанса не может быть позже даты конца сеанса.";

    public static final String START_END_BEFORE_CURRENT =
            "Дата начала или конца сеанса не может быть раньше текущей даты.";

    public static final String FILM_SESSION_NOT_FOUND = "Киносеанс не найден.";

    public static final String FILM_SESSION_DTO_ID_IS_INVALID =
            "Значение поля 'id' у 'FilmSession' не может быть null.";

    public static final String FILM_SESSION_DTO_NAME_IS_INVALID =
            "Значение поля 'name' у 'FilmSession' не может быть пустым.";

    public static final String FILM_SESSION_DTO_HALL_DTO_IS_INVALID =
            "Значение поля 'hallDto' у 'FilmSession' не может быть null.";

    public static final String FILM_SESSION_DTO_START_TIME_IS_INVALID =
            "Значение поля 'startTime' у 'FilmSession' не может быть null.";

    public static final String FILM_SESSION_DTO_END_TIME_IS_INVALID =
            "Значение поля 'endTime' у 'FilmSession' не может быть null.";

    public static final String FILM_SESSION_DTO_PRICE_IS_NULL =
            "Значение поля 'price' у 'FilmSession' не может быть null.";

    public static final String FILM_SESSION_DTO_PRICE_IS_INVALID =
            "Значение поля 'price' у 'FilmSession' не может быть меньше 0.";

    public static final String FILM_SESSION_CREATE_DTO_NAME_IS_INVALID =
            "Значение поля 'name' у 'FilmSessionCreateDto' не может быть пустым.";

    public static final String FILM_SESSION_CREATE_DTO_FILM_ID_IS_INVALID =
            "Значение поля 'filmId' у 'FilmSessionCreateDto' не может быть null.";

    public static final String FILM_SESSION_CREATE_DTO_HALL_ID_IS_INVALID =
            "Значение поля 'hall' у 'FilmSessionCreateDto' не может быть null.";

    public static final String FILM_SESSION_CREATE_DTO_START_TIME_IS_INVALID =
            "Значение поля 'startTime' у 'FilmSessionCreateDto' не может быть null.";

    public static final String FILM_SESSION_CREATE_DTO_END_TIME_IS_INVALID =
            "Значение поля 'endTime' у 'FilmSessionCreateDto' не может быть null.";
}
