package ru.films.exception.message;

import lombok.experimental.UtilityClass;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 06.05.2024
 */
@UtilityClass
public class CountryExceptionMessage {
    public static final String NAME_VALUE_IS_INVALID =
            "Значение поля 'name' у сущности 'Country' не может быть пустым.";
}


