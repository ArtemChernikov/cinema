package ru.films.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.films.model.BackDrop;

@Repository
public interface BackDropRepository extends JpaRepository<BackDrop, Long> {
}
