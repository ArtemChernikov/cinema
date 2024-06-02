package ru.films.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static ru.films.exception.message.RatingExceptionMessage.FILM_CRITICS_RATE_VALUE_IS_INVALID;
import static ru.films.exception.message.RatingExceptionMessage.IMDB_RATE_VALUE_IS_INVALID;
import static ru.films.exception.message.RatingExceptionMessage.KP_RATE_VALUE_IS_INVALID;
import static ru.films.exception.message.RatingExceptionMessage.RUSSIAN_FILM_CRITICS_RATE_VALUE_IS_INVALID;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 28.04.2024
 */
@Entity
@Table(name = "ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = KP_RATE_VALUE_IS_INVALID)
    @Column(name = "kp_rate")
    private Double kpRate;

    @NotNull(message = IMDB_RATE_VALUE_IS_INVALID)
    @Column(name = "imdb_rate")
    private Double imdbRate;

    @NotNull(message = FILM_CRITICS_RATE_VALUE_IS_INVALID)
    @Column(name = "film_critics_rate")
    private Double filmCriticsRate;

    @NotNull(message = RUSSIAN_FILM_CRITICS_RATE_VALUE_IS_INVALID)
    @Column(name = "russian_film_critics_rate")
    private Double russianFilmCriticsRate;
}
