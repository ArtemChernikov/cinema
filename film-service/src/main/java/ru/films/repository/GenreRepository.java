package ru.films.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.films.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
