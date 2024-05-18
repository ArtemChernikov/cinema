package ru.cinema.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.cinema.model.Hall;
import ru.cinema.model.dto.HallDto;
import ru.cinema.model.dto.HallShortDto;

import java.util.List;
import java.util.stream.IntStream;

@Mapper(componentModel = "spring")
public interface HallMapper {

    @Mapping(target = "rows", source = "rowCount", qualifiedByName = "countToListInteger")
    @Mapping(target = "places", source = "placeCount", qualifiedByName = "countToListInteger")
    HallDto hallToHallDto(Hall hall);

    HallShortDto hallToHallShortDto(Hall hall);

    @Named("countToListInteger")
    default List<Integer> countToListInteger(int count) {
        return IntStream.rangeClosed(1, count).boxed().toList();
    }

    @Named("createHallWithId")
    default Hall createHallWithId(Long id) {
        return Hall.builder().id(id).build();
    }

}
