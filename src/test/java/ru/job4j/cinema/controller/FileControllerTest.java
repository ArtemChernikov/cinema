package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cinema.dto.FileDto;
import ru.job4j.cinema.service.FileService;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class FileControllerTest {

    private FileService fileService;

    private FileController fileController;

    private MultipartFile testFile;

    @BeforeEach
    public void initServices() {
        fileService = mock(FileService.class);
        fileController = new FileController(fileService);
        testFile = new MockMultipartFile("testFile.img", new byte[]{1, 2, 3});
    }

    /**
     * Метод используется для проверки корректности работы метода в контроллере по поиску файла по id
     *
     * @throws IOException - {@link IOException}
     */
    @Test
    public void whenRequestFileByIdThenGetFile() throws IOException {
        var fileDto = new FileDto(testFile.getOriginalFilename(), testFile.getBytes());
        when(fileService.getFileById(any(Integer.class))).thenReturn(Optional.of(fileDto));

        var actualFile = fileController.getFileById(1);

        assertThat(fileDto.getContent()).isEqualTo(actualFile.getBody());
    }

    /**
     * Метод используется для проверки корректности работы метода в контроллере по поиску файла по id
     */
    @Test
    public void whenRequestFileByNonExistIdThenNotFoundFile() {
        when(fileService.getFileById(any(Integer.class))).thenReturn(Optional.empty());
        var actualFile = fileController.getFileById(1);
        assertThat(actualFile.getBody()).isNull();
    }
}