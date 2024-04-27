package ru.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cinema.model.Film;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
public interface FilmRepository extends JpaRepository<Film, Integer> {

}
