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
    public static final String ALTERNATIVE_NAME_VALUE_IS_INVALID =
            "Значение поля 'alternative_name' у сущности 'Film' не может быть пустым.";
    public static final String TYPE_VALUE_IS_INVALID =
            "Значение поля 'type' у сущности 'Film' не может быть пустым.";
    public static final String YEAR_VALUE_IS_INVALID =
            "Значение поля 'year' у сущности 'Film' не может быть null.";
    public static final String DESCRIPTION_VALUE_IS_INVALID =
            "Значение поля 'description' у сущности 'Film' не может быть пустым.";
    public static final String SHORT_DESCRIPTION_VALUE_IS_INVALID =
            "Значение поля 'shortDescription' у сущности 'Film' не может быть пустым.";
    public static final String RATING_VALUE_IS_INVALID =
            "Значение поля 'rating' у сущности 'Film' не может быть null.";
    public static final String MOVIE_LENGTH_VALUE_IS_INVALID =
            "Значение поля 'movieLength' у сущности 'Film' не может быть null.";
    public static final String MOVIE_LENGTH_MIN_VALUE_IS_INVALID =
            "Значение поля 'movieLength' у сущности 'Film' не может быть меньше или равно 0.";
    public static final String AGE_RATING_VALUE_IS_INVALID =
            "Значение поля 'ageRating' у сущности 'Film' не может быть null.";
    public static final String AGE_RATING_MIN_VALUE_IS_INVALID =
            "Значение поля 'ageRating' у сущности 'Film' не может быть меньше 0.";
    public static final String AGE_RATING_MAX_VALUE_IS_INVALID =
            "Значение поля 'ageRating' у сущности 'Film' не может быть больше 100.";
    public static final String POSTER_VALUE_IS_INVALID =
            "Значение поля 'poster' у сущности 'Film' не может быть null.";
}
