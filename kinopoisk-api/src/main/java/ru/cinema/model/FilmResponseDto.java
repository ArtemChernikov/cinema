package ru.cinema.model;

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
public class FilmResponseDto {
    private Long id;
    private String name;
    private String alternativeName;
    private String type;
    private String year;
    private String description;
    private String shortDescription;
    private RatingResponseDto rating;
    private Integer movieLength;
    private Integer ageRating;
    private PosterResponseDto poster;
    private List<String> genres;
    private List<String> countries;

}
