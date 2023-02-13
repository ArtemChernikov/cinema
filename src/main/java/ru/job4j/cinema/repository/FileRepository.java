package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.File;

import java.util.Optional;

/**
 * Общий интерфейс для всех репозиториев с файлами {@link File}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
public interface FileRepository {
    Optional<File> findById(int id);
}
