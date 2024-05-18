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
}
