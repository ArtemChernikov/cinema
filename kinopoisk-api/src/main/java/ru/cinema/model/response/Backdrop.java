package ru.cinema.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 03.05.2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Backdrop {
    private String url;
    private String previewUrl;
}
