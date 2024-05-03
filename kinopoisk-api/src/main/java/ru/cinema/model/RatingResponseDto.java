package ru.cinema.model;

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
public class RatingResponseDto {
    private Double kinopoiskRate;
    private Double imdbRate;
    private Double filmCriticsRate;
    private Double russianFilmCriticsRate;
}
