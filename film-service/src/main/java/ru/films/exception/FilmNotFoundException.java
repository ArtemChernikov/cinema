package ru.films.exception;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 08.05.2024
 */
public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(String message) {
        super(message);
    }
}
