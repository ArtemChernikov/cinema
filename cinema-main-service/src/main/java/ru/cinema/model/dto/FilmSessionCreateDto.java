package ru.cinema.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmSessionCreateDto {
    private String name;
    private Long filmId;
    private Long hallId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;
    private Integer price;
}
