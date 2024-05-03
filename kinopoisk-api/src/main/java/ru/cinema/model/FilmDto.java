package ru.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 28.04.2024
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

    private int fileId;

}
