package ru.cinema.exception;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 18.05.2024
 */
public class TicketExistsException extends RuntimeException {
    public TicketExistsException(String message) {
        super(message);
    }
}
