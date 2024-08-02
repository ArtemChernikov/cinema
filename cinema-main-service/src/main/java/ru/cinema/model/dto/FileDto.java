package ru.cinema.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static ru.cinema.exception.message.FileExceptionMessage.FILE_DTO_NAME_IS_INVALID;

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

    @NotEmpty(message = FILE_DTO_NAME_IS_INVALID)
    private String name;

    private byte[] content;

}
