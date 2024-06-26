package ru.cinema.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 24.06.2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAddFilms {
    private String collection;
}
