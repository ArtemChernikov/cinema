package ru.cinema.exception.message;

import lombok.experimental.UtilityClass;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 02.08.2024
 */
@UtilityClass
public class FileExceptionMessage {
    public static final String FILE_DTO_NAME_IS_INVALID = "Значение поля 'name' у 'FileDto' не может быть пустым.";
}
