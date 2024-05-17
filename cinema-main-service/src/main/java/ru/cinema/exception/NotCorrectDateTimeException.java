package ru.cinema.exception;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 17.05.2024
 */
public class NotCorrectDateTimeException extends RuntimeException {
    public NotCorrectDateTimeException(String message) {
        super(message);
    }
}
