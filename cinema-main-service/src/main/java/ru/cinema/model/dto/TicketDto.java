package ru.cinema.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.03.2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private long filmSessionId;

    private int rowNumber;

    private int placeNumber;

    private long userId;
}
