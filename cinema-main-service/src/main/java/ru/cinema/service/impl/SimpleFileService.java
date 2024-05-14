package ru.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import ru.cinema.model.dto.FileDto;
import ru.cinema.repository.FileRepository;
import ru.cinema.service.FileService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SimpleFileService implements FileService {

    private final FileRepository fileRepository;

    private byte[] readFileAsBytes(String path) {
        try {
            ClassPathResource classPathResource = new ClassPathResource(path);
            InputStream inputStream = classPathResource.getInputStream();
            return FileCopyUtils.copyToByteArray(inputStream);
        } catch (IOException e) {
            log.error("Error reading file", e);
        }
        return new byte[0];
    }

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
