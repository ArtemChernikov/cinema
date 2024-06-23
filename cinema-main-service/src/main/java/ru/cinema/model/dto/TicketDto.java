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
    private Long filmSessionId;
    private Integer rowNumber;
    private Integer placeNumber;
    private Long userId;
}
