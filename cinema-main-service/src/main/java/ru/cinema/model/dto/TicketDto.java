package ru.cinema.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static ru.cinema.exception.message.TicketExceptionMessage.TICKET_DTO_FILM_SESSION_ID_IS_INVALID;
import static ru.cinema.exception.message.TicketExceptionMessage.TICKET_DTO_FILM_SESSION_ID_IS_NULL;
import static ru.cinema.exception.message.TicketExceptionMessage.TICKET_DTO_PLACE_NUMBER_IS_INVALID;
import static ru.cinema.exception.message.TicketExceptionMessage.TICKET_DTO_PLACE_NUMBER_IS_NULL;
import static ru.cinema.exception.message.TicketExceptionMessage.TICKET_DTO_ROW_NUMBER_IS_INVALID;
import static ru.cinema.exception.message.TicketExceptionMessage.TICKET_DTO_ROW_NUMBER_IS_NULL;
import static ru.cinema.exception.message.TicketExceptionMessage.TICKET_DTO_USER_ID_IS_INVALID;
import static ru.cinema.exception.message.TicketExceptionMessage.TICKET_DTO_USER_ID_IS_NULL;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.03.2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    @NotNull(message = TICKET_DTO_FILM_SESSION_ID_IS_NULL)
    @Min(value = 1, message = TICKET_DTO_FILM_SESSION_ID_IS_INVALID)
    private Long filmSessionId;

    @NotNull(message = TICKET_DTO_ROW_NUMBER_IS_NULL)
    @Min(value = 1, message = TICKET_DTO_ROW_NUMBER_IS_INVALID)
    private Integer rowNumber;

    @NotNull(message = TICKET_DTO_PLACE_NUMBER_IS_NULL)
    @Min(value = 1, message = TICKET_DTO_PLACE_NUMBER_IS_INVALID)
    private Integer placeNumber;

    @NotNull(message = TICKET_DTO_USER_ID_IS_NULL)
    @Min(value = 1, message = TICKET_DTO_USER_ID_IS_INVALID)
    private Long userId;
}
