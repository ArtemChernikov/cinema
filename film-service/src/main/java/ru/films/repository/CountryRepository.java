package ru.films.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.films.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
