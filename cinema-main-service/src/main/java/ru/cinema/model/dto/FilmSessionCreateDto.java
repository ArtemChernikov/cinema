package ru.cinema.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static ru.cinema.exception.message.FilmSessionExceptionMessage.FILM_SESSION_CREATE_DTO_END_TIME_IS_INVALID;
import static ru.cinema.exception.message.FilmSessionExceptionMessage.FILM_SESSION_CREATE_DTO_FILM_ID_IS_INVALID;
import static ru.cinema.exception.message.FilmSessionExceptionMessage.FILM_SESSION_CREATE_DTO_HALL_ID_IS_INVALID;
import static ru.cinema.exception.message.FilmSessionExceptionMessage.FILM_SESSION_CREATE_DTO_NAME_IS_INVALID;
import static ru.cinema.exception.message.FilmSessionExceptionMessage.FILM_SESSION_CREATE_DTO_START_TIME_IS_INVALID;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmSessionCreateDto {
    @NotEmpty(message = FILM_SESSION_CREATE_DTO_NAME_IS_INVALID)
    private String name;

    @NotNull(message = FILM_SESSION_CREATE_DTO_FILM_ID_IS_INVALID)
    private Long filmId;

    @NotNull(message = FILM_SESSION_CREATE_DTO_HALL_ID_IS_INVALID)
    private Long hallId;

    @NotNull(message = FILM_SESSION_CREATE_DTO_START_TIME_IS_INVALID)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;

    @NotNull(message = FILM_SESSION_CREATE_DTO_END_TIME_IS_INVALID)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;
    private Integer price;
}
