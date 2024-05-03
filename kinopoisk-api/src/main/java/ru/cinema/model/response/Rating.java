package ru.cinema.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 03.05.2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private double kp;
    private double imdb;
    private double filmCritics;
    private double russianFilmCritics;
}
