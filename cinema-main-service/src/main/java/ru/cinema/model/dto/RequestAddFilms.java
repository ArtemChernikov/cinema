package ru.cinema.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static ru.cinema.exception.message.FilmExceptionMessage.REQUEST_ADD_FILMS_COLLECTION_IS_INVALID;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 24.06.2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAddFilms {
    @NotEmpty(message = REQUEST_ADD_FILMS_COLLECTION_IS_INVALID)
    private String collection;
}
