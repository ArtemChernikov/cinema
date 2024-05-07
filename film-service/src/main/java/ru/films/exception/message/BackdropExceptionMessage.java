package ru.films.exception.message;

import lombok.experimental.UtilityClass;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 06.05.2024
 */
@UtilityClass
public class BackdropExceptionMessage {
    public static final String URL_VALUE_IS_INVALID =
            "Значение поля 'url' у сущности 'Backdrop' не может быть пустым.";
    public static final String PREVIEW_URL_VALUE_IS_INVALID =
            "Значение поля 'previewUrl' у сущности 'Backdrop' не может быть пустым.";
}
