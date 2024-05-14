package ru.cinema.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 14.02.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {

    private String name;

    private String description;

    private int year;

    private String genre;

    private int minimalAge;

    private int durationInMinutes;

}
