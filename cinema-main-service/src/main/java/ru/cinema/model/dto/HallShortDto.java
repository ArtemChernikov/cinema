package ru.cinema.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static ru.cinema.exception.message.HallExceptionMessage.HALL_SHORT_DTO_ID_IS_INVALID;
import static ru.cinema.exception.message.HallExceptionMessage.HALL_SHORT_DTO_NAME_IS_INVALID;

/**
 * @author Artem Chernikov
 * @version 1.1
 * @since 17.05.2024
 */
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class HallShortDto {
    @NotNull(message = HALL_SHORT_DTO_ID_IS_INVALID)
    private Long id;

    @NotEmpty(message = HALL_SHORT_DTO_NAME_IS_INVALID)
    private String name;
}
