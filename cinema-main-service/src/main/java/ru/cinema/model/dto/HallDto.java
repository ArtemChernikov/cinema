package ru.cinema.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static ru.cinema.exception.message.HallExceptionMessage.HALL_DTO_NAME_IS_INVALID;
import static ru.cinema.exception.message.HallExceptionMessage.HALL_DTO_PLACES_IS_EMPTY;
import static ru.cinema.exception.message.HallExceptionMessage.HALL_DTO_PLACES_IS_NULL;
import static ru.cinema.exception.message.HallExceptionMessage.HALL_DTO_ROWS_IS_EMPTY;
import static ru.cinema.exception.message.HallExceptionMessage.HALL_DTO_ROWS_IS_NULL;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 18.05.2024
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HallDto {

    @NotEmpty(message = HALL_DTO_NAME_IS_INVALID)
    private String name;

    @NotNull(message = HALL_DTO_ROWS_IS_NULL)
    @NotEmpty(message = HALL_DTO_ROWS_IS_EMPTY)
    private List<Integer> rows;

    @NotNull(message = HALL_DTO_PLACES_IS_NULL)
    @NotEmpty(message = HALL_DTO_PLACES_IS_EMPTY)
    private List<Integer> places;
}
