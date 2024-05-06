package ru.films.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.films.model.Film;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    @Override
    @NonNull
    @Query("select f " +
            "from Film as f " +
            "join fetch f.genres " +
            "join fetch f.countries")
    List<Film> findAll();
}
