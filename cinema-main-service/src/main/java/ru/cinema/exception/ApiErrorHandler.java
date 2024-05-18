package ru.cinema.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 08.05.2024
 */
@Slf4j
@ControllerAdvice
public class ApiErrorHandler {

    @ExceptionHandler({NotFoundException.class})
    public String notFoundException(final RuntimeException e, Model model) {
        log.warn("cinema-main-service ApiErrorHandler: " + HttpStatus.NOT_FOUND + " {}", e.getMessage());
        model.addAttribute("message", e.getMessage());
        return "errors/404";
    }

    @ExceptionHandler({NotCorrectDateTimeException.class, MethodArgumentTypeMismatchException.class})
    public String badRequestException(final RuntimeException e, Model model) {
        log.warn("cinema-main-service ApiErrorHandler: " + HttpStatus.BAD_REQUEST + " {}", e.getMessage());
        model.addAttribute("message", e.getMessage());
        return "errors/400";
    }

    @ExceptionHandler({TicketExistsException.class, UserExistsException.class})
    public String conflictException(final RuntimeException e, Model model) {
        log.warn("cinema-main-service ApiErrorHandler: " + HttpStatus.CONFLICT + " {}", e.getMessage());
        model.addAttribute("message", e.getMessage());
        return "errors/409";
    }
}
