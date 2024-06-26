package ru.cinema.model.dto.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 26.06.2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDto {
    @NotEmpty
    private String name;

    @NotEmpty
    private String searchName;
}
