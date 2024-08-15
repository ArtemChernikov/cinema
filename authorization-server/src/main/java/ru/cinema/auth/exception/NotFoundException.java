package ru.cinema.auth.exception;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.08.2024
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
