package ru.films.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 28.04.2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    private Long id;
    private Double kpRate;
    private Double imdbRate;
    private Double filmCriticsRate;
    private Double russianFilmCriticsRate;
}
