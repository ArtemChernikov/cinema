package ru.cinema.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.cinema.service.FileService;

/**
 *
 * @author Artem Chernikov
 * @version 1.1
 * @since 23.02.2023
 */
@ThreadSafe
@Slf4j
@RequiredArgsConstructor
@Controller
public class FileController {

    private final FileService fileService;

    @GetMapping({"/posters/{id}", "/halls/{id}"})
    public ResponseEntity<?> getFileById(@PathVariable("id") Long id) {
        log.info("cinema-main-service: выполнение запроса на получение файла по id: {}", id);
        var contentOptional = fileService.getFileById(id);
        if (contentOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contentOptional.get().getContent());
    }
}
