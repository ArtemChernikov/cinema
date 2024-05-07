package ru.films.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ru.films.model.Country;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    @Named("listCountriesToStringListCountries")
    default Set<String> listCountriesToStringListCountries(Set<Country> countries) {
        return countries.stream().map(Country::getName).collect(Collectors.toSet());
    }
}
