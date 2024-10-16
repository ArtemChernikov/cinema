package ru.films.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ru.films.model.Genre;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    @Named("listGenresToStringListGenres")
    default Set<String> listGenresToStringListGenres(Set<Genre> genres) {
        return genres.stream().map(Genre::getName).collect(Collectors.toSet());
    }

    @Named("stringListGenresToListGenres")
    default Set<Genre> stringListGenresToListGenres(List<String> genres) {
        return genres.stream().map(genre -> Genre.builder().name(genre).build()).collect(Collectors.toSet());
    }
}
