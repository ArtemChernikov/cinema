package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FileDto;
import ru.job4j.cinema.model.File;
import ru.job4j.cinema.repository.FileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Класс-сервис для работы с файлами {@link File} и DTO {@link FileDto}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
@Service
public class SimpleFileService implements FileService {

    private final FileRepository fileRepository;

    public SimpleFileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * Метод используется для чтения контента из файла по определенному пути
     *
     * @param path - путь для чтения
     * @return - возвращает массив байт
     */
    private byte[] readFileAsBytes(String path) {
        try {
            return Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод используется для поиска {@link File} по его id в репозитории
     * и дальнейшей загрузки контента в DTO {@link FileDto}
     *
     * @param id - id файла {@link File}
     * @return - возвращает {@link Optional<FileDto>}
     */
    @Override
    public Optional<FileDto> getFileById(int id) {
        var file = fileRepository.findById(id);
        if (file.isEmpty()) {
            return Optional.empty();
        }
        var content = readFileAsBytes(file.get().getPath());
        return Optional.of(new FileDto(file.get().getName(), content));
    }
}
