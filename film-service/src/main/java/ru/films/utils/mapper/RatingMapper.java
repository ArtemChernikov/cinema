package ru.films.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.films.model.Rating;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    @Mapping(target = "kpRate", source = "kp")
    @Mapping(target = "imdbRate", source = "imdb")
    @Mapping(target = "filmCriticsRate", source = "filmCritics")
    @Mapping(target = "russianFilmCriticsRate", source = "russianFilmCritics")
    Rating responseRatingToResponseEntity(ru.films.model.response.Rating rating);
}
