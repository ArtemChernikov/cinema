package ru.cinema.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 07.05.2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {
    private Double kpRate;
    private Double imdbRate;
    private Double filmCriticsRate;
    private Double russianFilmCriticsRate;
}
