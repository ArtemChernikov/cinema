package ru.cinema.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 03.05.2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    private Long id;
    private String name;
    private String alternativeName;
    private String enName;
    private List<Name> names;
    private String type;
    private int typeNumber;
    private String year;
    private String description;
    private String shortDescription;
    private String status;
    private Rating rating;
    private Votes votes;
    private Integer movieLength;
    private String totalSeriesLength;
    private String seriesLength;
    private String ratingMpaa;
    private Integer ageRating;
    private Poster poster;
    private Backdrop backdrop;
    private List<Genre> genres;
    private List<Country> countries;
    private Integer top10;
    private Integer top250;
    private boolean isSeries;
    private boolean ticketsOnSale;
}
