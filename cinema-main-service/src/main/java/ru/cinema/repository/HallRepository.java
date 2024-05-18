package ru.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.cinema.model.Hall;
import ru.cinema.model.dto.HallShortDto;

import java.util.List;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 13.02.2023
 */
public interface HallRepository extends JpaRepository<Hall, Long> {
    @Query("SELECT new ru.cinema.model.dto.HallShortDto(h.id, h.name) FROM Hall h")
    List<HallShortDto> findAllHallsAsDto();
}
