package ru.cinema.service;

import ru.cinema.model.dto.FileDto;

import java.util.Optional;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
public interface FileService {
    Optional<FileDto> getFileById(Long id);
}
