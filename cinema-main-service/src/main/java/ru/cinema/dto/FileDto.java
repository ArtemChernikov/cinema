package ru.cinema.dto;

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
public class FileDto {

    private String name;

    private byte[] content;

}
