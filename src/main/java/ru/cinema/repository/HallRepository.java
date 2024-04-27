package ru.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cinema.model.Hall;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
public interface HallRepository extends JpaRepository<Hall, Integer> {
}
