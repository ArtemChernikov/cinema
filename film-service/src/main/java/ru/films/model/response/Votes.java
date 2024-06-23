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
    private Integer kp;
    private Integer imdb;
    private Integer filmCritics;
    private Integer russianFilmCritics;
    private Integer await;
}
