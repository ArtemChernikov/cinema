package ru.films.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.films.model.Film;
import ru.films.model.dto.FilmDto;
import ru.films.model.response.Document;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RatingMapper.class, CountryMapper.class, GenreMapper.class})
public interface FilmMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "alternativeName", source = "alternativeName")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "year", source = "year")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "shortDescription", source = "shortDescription")
    @Mapping(target = "rating", source = "rating")
    @Mapping(target = "movieLength", source = "movieLength")
    @Mapping(target = "ageRating", source = "ageRating")
    @Mapping(target = "poster", source = "poster")
    @Mapping(target = "backdrop", source = "backdrop")
    @Mapping(target = "genres", source = "genres")
    @Mapping(target = "countries", source = "countries")
    Film documentToFilm(Document document);

    List<Film> documentListToFilmList(List<Document> documentList);

    @Mapping(source = "genres", target = "genres", qualifiedByName = "listGenresToStringListGenres")
    @Mapping(source = "countries", target = "countries", qualifiedByName = "listCountriesToStringListCountries")
    FilmDto filmToFilmDto(Film film);

    List<FilmDto> filmListToFilmDtoList(List<Film> filmList);
}
