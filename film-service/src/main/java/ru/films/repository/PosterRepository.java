package ru.films.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.films.model.Poster;

@Repository
public interface PosterRepository extends JpaRepository<Poster, Long> {
}
