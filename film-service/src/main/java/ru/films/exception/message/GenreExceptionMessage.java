package ru.films.exception.message;

import lombok.experimental.UtilityClass;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 06.05.2024
 */
@UtilityClass
public class GenreExceptionMessage {
    public static final String NAME_VALUE_IS_INVALID =
            "Значение поля 'name' у сущности 'Genre' не может быть пустым.";
}


