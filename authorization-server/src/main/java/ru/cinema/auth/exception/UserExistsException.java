package ru.cinema.auth.exception;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 18.05.2024
 */
public class UserExistsException extends RuntimeException {
    public UserExistsException(String message) {
        super(message);
    }
}
