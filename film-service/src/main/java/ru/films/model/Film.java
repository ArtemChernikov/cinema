package ru.films.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import jakarta.persistence.*;

import static ru.films.exception.message.FilmExceptionMessage.NAME_VALUE_IS_INVALID;
import static ru.films.exception.message.FilmExceptionMessage.POSTER_VALUE_IS_INVALID;
import static ru.films.exception.message.FilmExceptionMessage.RATING_VALUE_IS_INVALID;
import static ru.films.exception.message.FilmExceptionMessage.TYPE_VALUE_IS_INVALID;
import static ru.films.exception.message.FilmExceptionMessage.YEAR_VALUE_IS_INVALID;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 28.04.2024
 */
@Entity
@Table(name = "films")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    private Long id;

    @NotBlank(message = NAME_VALUE_IS_INVALID)
    private String name;

    @Column(name = "alternative_name")
    private String alternativeName;

    @NotBlank(message = TYPE_VALUE_IS_INVALID)
    private String type;

    @NotEmpty(message = YEAR_VALUE_IS_INVALID)
    private String year;

    private String description;

    @Column(name = "short_description")
    private String shortDescription;

    @NotNull(message = RATING_VALUE_IS_INVALID)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "rating_id")
    private Rating rating;

    @Column(name = "movie_length")
    private Integer movieLength;

    @Column(name = "age_rating")
    private Integer ageRating;

    @NotNull(message = POSTER_VALUE_IS_INVALID)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "poster_id")
    private Poster poster;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "backdrop_id")
    private Backdrop backdrop;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "films_genres",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    private Set<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "films_countries",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "country_id")})
    private Set<Country> countries;
}
