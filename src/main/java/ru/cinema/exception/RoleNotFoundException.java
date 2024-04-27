package ru.cinema.exception;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 26.02.2024
 */
public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
