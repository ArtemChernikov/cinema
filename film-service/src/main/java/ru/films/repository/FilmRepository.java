package ru.films.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.films.model.Film;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    @Override
    @NonNull
    @EntityGraph(attributePaths = {"genres", "countries"})
    List<Film> findAll();

    boolean existsByName(String name);
}
