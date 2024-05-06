package ru.films.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.films.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
