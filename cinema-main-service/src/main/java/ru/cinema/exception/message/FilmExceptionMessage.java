package ru.cinema.exception.message;

import lombok.experimental.UtilityClass;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 02.08.2024
 */
@UtilityClass
public class FilmExceptionMessage {
    public static final String REQUEST_ADD_FILMS_COLLECTION_IS_INVALID =
            "Значение поля 'collection' у 'RequestAddFilms' не может быть пустым.";
}
