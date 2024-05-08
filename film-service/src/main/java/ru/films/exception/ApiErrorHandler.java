package ru.films.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 08.05.2024
 */
@Slf4j
@RestControllerAdvice
public class ApiErrorHandler {

    @ExceptionHandler({FilmNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError badRequestException(final RuntimeException e) {
        log.info(HttpStatus.NOT_FOUND + " {}", e.getMessage());
        return ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .reason("Ресурс не найден.")
                .message(e.getLocalizedMessage())
                .errorTimestamp(LocalDateTime.now())
                .build();
    }

}
