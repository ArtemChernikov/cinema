package ru.cinema.service;

import ru.cinema.dto.FileDto;
import ru.cinema.model.File;

import java.util.Optional;

/**
 * Общий интерфейс для всех классов-сервисов по работе с файлами {@link File} и DTO {@link FileDto}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
public interface FileService {
    Optional<FileDto> getFileById(int id);
}
