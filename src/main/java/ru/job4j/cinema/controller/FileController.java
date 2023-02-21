package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.FileService;
import ru.job4j.cinema.dto.FileDto;

/**
 * Класс-контроллер для работы с {@link FileDto}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 21.02.2023
 */
@ThreadSafe
@Controller
@RequestMapping("/posters")
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
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        var contentOptional = fileService.getFileById(id);
        if (contentOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contentOptional.get().getContent());
    }
}
