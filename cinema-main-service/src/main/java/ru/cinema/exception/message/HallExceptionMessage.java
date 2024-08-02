package ru.cinema.exception.message;

import lombok.experimental.UtilityClass;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 02.08.2024
 */
@UtilityClass
public class HallExceptionMessage {
    public static final String HALL_DTO_NAME_IS_INVALID =
            "Значение поля 'name' у 'HallDto' не может быть пустым.";

    public static final String HALL_DTO_ROWS_IS_NULL =
            "Значение поля 'rows' у 'HallDto' не может быть null.";

    public static final String HALL_DTO_ROWS_IS_EMPTY =
            "Значение поля 'rows' у 'HallDto' не может быть пустым.";

    public static final String HALL_DTO_PLACES_IS_NULL =
            "Значение поля 'places' у 'HallDto' не может быть null.";

    public static final String HALL_DTO_PLACES_IS_EMPTY =
            "Значение поля 'places' у 'HallDto' не может быть пустым.";

    public static final String HALL_SHORT_DTO_ID_IS_INVALID =
            "Значение поля 'id' у 'HallShortDto' не может быть null.";

    public static final String HALL_SHORT_DTO_NAME_IS_INVALID =
            "Значение поля 'name' у 'HallShortDto' не может быть пустым.";

}

