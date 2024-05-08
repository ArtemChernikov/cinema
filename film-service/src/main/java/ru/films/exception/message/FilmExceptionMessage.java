package ru.films.exception.message;

import lombok.experimental.UtilityClass;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 06.05.2024
 */
@UtilityClass
public class FilmExceptionMessage {
    public static final String NAME_VALUE_IS_INVALID =
            "Значение поля 'name' у сущности 'Film' не может быть пустым.";
    public static final String TYPE_VALUE_IS_INVALID =
            "Значение поля 'type' у сущности 'Film' не может быть пустым.";
    public static final String YEAR_VALUE_IS_INVALID =
            "Значение поля 'year' у сущности 'Film' не может быть null.";
    public static final String RATING_VALUE_IS_INVALID =
            "Значение поля 'rating' у сущности 'Film' не может быть null.";
    public static final String POSTER_VALUE_IS_INVALID =
            "Значение поля 'poster' у сущности 'Film' не может быть null.";
    public static final String FILM_NOT_FOUND = "Фильм по данному id не найден.";
}
