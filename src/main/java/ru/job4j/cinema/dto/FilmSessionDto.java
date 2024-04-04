package ru.job4j.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.cinema.model.FilmSession;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) для класса {@link FilmSession}
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
