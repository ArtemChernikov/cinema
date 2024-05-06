package ru.films.exception.message;

import lombok.experimental.UtilityClass;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 06.05.2024
 */
@UtilityClass
public class RatingExceptionMessage {
    public static final String KP_RATE_VALUE_IS_INVALID =
            "Значение поля 'kpRate' у сущности 'Rating' не может быть null.";
    public static final String IMDB_RATE_VALUE_IS_INVALID =
            "Значение поля 'imdbRate' у сущности 'Rating' не может быть null.";
    public static final String FILM_CRITICS_RATE_VALUE_IS_INVALID =
            "Значение поля 'filmCriticsRate' у сущности 'Rating' не может быть null.";
    public static final String RUSSIAN_FILM_CRITICS_RATE_VALUE_IS_INVALID =
            "Значение поля 'russianFilmCriticsRate' у сущности 'Rating' не может быть null.";
}
