package ru.films.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 28.04.2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    private Long id;
    private String name;
    private String alternativeName;
    private String type;
    private String year;
    private String description;
    private String shortDescription;
    private Rating rating;
    private Integer movieLength;
    private Integer ageRating;
    private Poster poster;
    private List<String> genres;
    private List<String> countries;
}
