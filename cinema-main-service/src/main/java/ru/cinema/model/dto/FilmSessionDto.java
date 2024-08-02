package ru.cinema.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static ru.cinema.exception.message.FilmSessionExceptionMessage.FILM_SESSION_DTO_END_TIME_IS_INVALID;
import static ru.cinema.exception.message.FilmSessionExceptionMessage.FILM_SESSION_DTO_HALL_DTO_IS_INVALID;
import static ru.cinema.exception.message.FilmSessionExceptionMessage.FILM_SESSION_DTO_ID_IS_INVALID;
import static ru.cinema.exception.message.FilmSessionExceptionMessage.FILM_SESSION_DTO_NAME_IS_INVALID;
import static ru.cinema.exception.message.FilmSessionExceptionMessage.FILM_SESSION_DTO_PRICE_IS_INVALID;
import static ru.cinema.exception.message.FilmSessionExceptionMessage.FILM_SESSION_DTO_PRICE_IS_NULL;
import static ru.cinema.exception.message.FilmSessionExceptionMessage.FILM_SESSION_DTO_START_TIME_IS_INVALID;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmSessionDto {

    @NotNull(message = FILM_SESSION_DTO_ID_IS_INVALID)
    private Long id;

    @NotEmpty(message = FILM_SESSION_DTO_NAME_IS_INVALID)
    private String name;

    @NotNull(message = FILM_SESSION_DTO_HALL_DTO_IS_INVALID)
    private HallDto hallDto;

    @NotNull(message = FILM_SESSION_DTO_START_TIME_IS_INVALID)
    private LocalDateTime startTime;

    @NotNull(message = FILM_SESSION_DTO_END_TIME_IS_INVALID)
    private LocalDateTime endTime;

    @NotNull(message = FILM_SESSION_DTO_PRICE_IS_NULL)
    @Min(value = 0, message = FILM_SESSION_DTO_PRICE_IS_INVALID)
    private Integer price;

}
