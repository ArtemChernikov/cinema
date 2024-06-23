package ru.cinema.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmSessionDto {

    private Long id;

    private String name;

    private HallDto hallDto;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer price;

}
