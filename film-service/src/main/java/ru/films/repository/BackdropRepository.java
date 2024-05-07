package ru.films.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.films.model.Backdrop;

@Repository
public interface BackdropRepository extends JpaRepository<Backdrop, Long> {
}
