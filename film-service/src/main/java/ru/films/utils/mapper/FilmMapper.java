package ru.films.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.films.model.Film;
import ru.films.model.response.Document;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RatingMapper.class})
public interface FilmMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "alternativeName", source = "alternativeName")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "year", expression = "java(Integer.parseInt(document.getYear()))")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "shortDescription", source = "shortDescription")
    @Mapping(target = "rating", source = "rating")
    @Mapping(target = "movieLength", source = "movieLength")
    @Mapping(target = "ageRating", source = "ageRating")
    @Mapping(target = "poster", source = "poster")
    @Mapping(target = "genres", source = "genres")
    @Mapping(target = "countries", source = "countries")
    Film documentToFilm(Document document);

    List<Film> documentListToFilmList(List<Document> documentList);

}
