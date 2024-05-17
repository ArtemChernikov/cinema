package ru.cinema.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 08.05.2024
 */
@Slf4j
@ControllerAdvice
public class ApiErrorHandler {

    @ExceptionHandler({FilmNotFoundException.class})
    public String notFoundException(final RuntimeException e, Model model) {
        log.warn("cinema-main-service ApiErrorHandler: " + HttpStatus.NOT_FOUND + " {}", e.getMessage());
        model.addAttribute("message", e.getMessage());
        return "errors/404";
    }

    @ExceptionHandler({NotCorrectDateTimeException.class})
    public String dateTimeException(final RuntimeException e, Model model) {
        log.warn("cinema-main-service ApiErrorHandler: " + HttpStatus.BAD_REQUEST + " {}", e.getMessage());
        model.addAttribute("message", e.getMessage());
        return "errors/404";
    }

}
