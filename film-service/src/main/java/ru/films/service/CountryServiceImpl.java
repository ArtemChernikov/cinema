package ru.films.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.films.model.Country;
import ru.films.repository.CountryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 12.06.2024
 */
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public Map<String, Country> saveNotExistsCountry(Set<String> countryNames) {
        List<Country> existingCountries = countryRepository.findByNameIn(new ArrayList<>(countryNames));
        Set<String> existingCountryNames = existingCountries.stream()
                .map(Country::getName)
                .collect(Collectors.toSet());
        List<Country> newCountries = countryNames.stream()
                .filter(name -> !existingCountryNames.contains(name))
                .map(name -> Country.builder().name(name).build())
                .toList();
        newCountries = countryRepository.saveAll(newCountries);
        return Stream.concat(existingCountries.stream(), newCountries.stream())
                .collect(Collectors.toMap(Country::getName, Function.identity()));
    }
}
