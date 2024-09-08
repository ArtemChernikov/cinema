package ru.films.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 07.05.2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {
    private Long id;
    private String name;
    private String alternativeName;
    private String type;
    private String year;
    private String description;
    private String shortDescription;
    private RatingDto rating;
    private Integer movieLength;
    private Integer ageRating;
    private PosterDto poster;
    private BackdropDto backdrop;
    private Set<String> genres;
    private Set<String> countries;
}
