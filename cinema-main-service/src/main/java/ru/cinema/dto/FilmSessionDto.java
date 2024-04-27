package ru.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmSessionDto {

    private int id;

    private String film;

    private String hall;

    private int hallId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int price;

}
