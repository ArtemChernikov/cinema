package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.job4j.cinema.service.FileService;
import ru.job4j.cinema.dto.FileDto;

/**
 * Класс-контроллер для работы с {@link FileDto}
 *
 * @author Artem Chernikov
 * @version 1.1
 * @since 23.02.2023
 */
@ThreadSafe
@Controller
public class FileController {
    /**
     * Поле сервис для работы с файлами
     */
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Метод используется для взаимодействия с файлом по id,
     * если файл не найден по id, то клиенту возвращается статус 404,
     * а если найден, то статус 200 с телом ответа в виде содержимого файла
     *
     * @param id - id файла
     * @return - возвращает файл в отображении
     */
    @GetMapping({"/posters/{id}", "/halls/{id}"})
    public ResponseEntity<?> getFileById(@PathVariable int id) {
        var contentOptional = fileService.getFileById(id);
        if (contentOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contentOptional.get().getContent());
    }
}
