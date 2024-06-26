package ru.films.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.films.model.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
}
