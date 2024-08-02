package ru.cinema.exception.message;

import lombok.experimental.UtilityClass;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 18.05.2024
 */
@UtilityClass
public class TicketExceptionMessage {
    public static final String TICKET_ALREADY_EXISTS = """
            Не удалось приобрести билет на заданное место. Вероятно оно уже занято.
            Перейдите на страницу бронирования билетов и попробуйте купить другой билет.
            """;

    public static final String TICKET_DTO_FILM_SESSION_ID_IS_NULL =
            "Значение поля 'filmSessionId' у 'TicketDto' не может быть null.";

    public static final String TICKET_DTO_FILM_SESSION_ID_IS_INVALID =
            "Значение поля 'filmSessionId' у 'TicketDto' не может быть меньше 1.";

    public static final String TICKET_DTO_ROW_NUMBER_IS_NULL =
            "Значение поля 'rowNumber' у 'TicketDto' не может быть null.";

    public static final String TICKET_DTO_ROW_NUMBER_IS_INVALID =
            "Значение поля 'rowNumber' у 'TicketDto' не может быть меньше 1.";

    public static final String TICKET_DTO_PLACE_NUMBER_IS_NULL =
            "Значение поля 'placeNumber' у 'TicketDto' не может быть null.";

    public static final String TICKET_DTO_PLACE_NUMBER_IS_INVALID =
            "Значение поля 'placeNumber' у 'TicketDto' не может быть меньше 1.";

    public static final String TICKET_DTO_USER_ID_IS_NULL =
            "Значение поля 'userId' у 'TicketDto' не может быть null.";

    public static final String TICKET_DTO_USER_ID_IS_INVALID =
            "Значение поля 'userId' у 'TicketDto' не может быть меньше 1.";

}
