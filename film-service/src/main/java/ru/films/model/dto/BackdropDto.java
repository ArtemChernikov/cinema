package ru.films.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 07.05.2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackdropDto {
    private String url;
    private String previewUrl;
}
