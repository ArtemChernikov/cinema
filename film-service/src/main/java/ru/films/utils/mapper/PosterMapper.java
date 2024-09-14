package ru.films.utils.mapper;

import org.mapstruct.Mapper;
import ru.films.model.Poster;
import ru.films.model.dto.PosterDto;

@Mapper(componentModel = "spring")
public interface PosterMapper {
    PosterDto posterToPosterDto(Poster poster);
}
