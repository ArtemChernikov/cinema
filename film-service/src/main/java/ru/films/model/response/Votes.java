package ru.films.model.response;

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
public class Votes {
    private int kp;
    private int imdb;
    private int filmCritics;
    private int russianFilmCritics;
    private int await;
}
