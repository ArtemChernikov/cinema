package ru.job4j.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.cinema.model.FilmSession;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
public interface FilmSessionRepository extends JpaRepository<FilmSession, Integer> {
}
