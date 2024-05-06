package ru.films.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static ru.films.exception.message.FilmExceptionMessage.AGE_RATING_MAX_VALUE_IS_INVALID;
import static ru.films.exception.message.FilmExceptionMessage.AGE_RATING_MIN_VALUE_IS_INVALID;
import static ru.films.exception.message.FilmExceptionMessage.AGE_RATING_VALUE_IS_INVALID;
import static ru.films.exception.message.FilmExceptionMessage.ALTERNATIVE_NAME_VALUE_IS_INVALID;
import static ru.films.exception.message.FilmExceptionMessage.DESCRIPTION_VALUE_IS_INVALID;
import static ru.films.exception.message.FilmExceptionMessage.MOVIE_LENGTH_MIN_VALUE_IS_INVALID;
import static ru.films.exception.message.FilmExceptionMessage.MOVIE_LENGTH_VALUE_IS_INVALID;
import static ru.films.exception.message.FilmExceptionMessage.NAME_VALUE_IS_INVALID;
import static ru.films.exception.message.FilmExceptionMessage.POSTER_VALUE_IS_INVALID;
import static ru.films.exception.message.FilmExceptionMessage.RATING_VALUE_IS_INVALID;
import static ru.films.exception.message.FilmExceptionMessage.SHORT_DESCRIPTION_VALUE_IS_INVALID;
import static ru.films.exception.message.FilmExceptionMessage.TYPE_VALUE_IS_INVALID;
import static ru.films.exception.message.FilmExceptionMessage.YEAR_VALUE_IS_INVALID;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 28.04.2024
 */
@Table(name = "films")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    private Long id;

    @NotBlank(message = NAME_VALUE_IS_INVALID)
    private String name;

    @NotBlank(message = ALTERNATIVE_NAME_VALUE_IS_INVALID)
    @Column(name = "alternative_name")
    private String alternativeName;

    @NotBlank(message = TYPE_VALUE_IS_INVALID)
    private String type;

    @NotNull(message = YEAR_VALUE_IS_INVALID)
    private Integer year;

    @NotBlank(message = DESCRIPTION_VALUE_IS_INVALID)
    private String description;

    @NotBlank(message = SHORT_DESCRIPTION_VALUE_IS_INVALID)
    @Column(name = "short_description")
    private String shortDescription;

    @NotNull(message = RATING_VALUE_IS_INVALID)
    private Rating rating;

    @NotNull(message = MOVIE_LENGTH_VALUE_IS_INVALID)
    @Min(value = 1, message = MOVIE_LENGTH_MIN_VALUE_IS_INVALID)
    @Column(name = "movie_length")
    private Integer movieLength;

    @NotNull(message = AGE_RATING_VALUE_IS_INVALID)
    @Min(value = 0, message = AGE_RATING_MIN_VALUE_IS_INVALID)
    @Max(value = 100, message = AGE_RATING_MAX_VALUE_IS_INVALID)
    @Column(name = "age_rating")
    private Integer ageRating;

    @NotNull(message = POSTER_VALUE_IS_INVALID)
    private Poster poster;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "films_genres",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    private List<String> genres;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "films_countries",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "country_id")})
    private List<String> countries;
}
