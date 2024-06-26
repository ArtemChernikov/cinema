package ru.cinema.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 18.05.2024
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HallDto {

    private String name;

    private List<Integer> rows;

    private List<Integer> places;
}
