package ru.cinema.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.cinema.model.FilmSession;
import ru.cinema.model.dto.FilmSessionCreateDto;
import ru.cinema.model.dto.FilmSessionDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = {HallMapper.class})
public interface FilmSessionMapper {

    @Mapping(target = "hallDto", source = "hall")
    FilmSessionDto filmSessionToFilmSessionDto(FilmSession filmSession);

    @Mapping(target = "hall", source = "hallId", qualifiedByName = "createHallWithId")
    FilmSession filmSessionCreateDtoToFilmSession(FilmSessionCreateDto filmSessionCreateDto);

    List<FilmSessionDto> filmSessionListToFilmSessionListDto(List<FilmSession> filmSessionList);
}
