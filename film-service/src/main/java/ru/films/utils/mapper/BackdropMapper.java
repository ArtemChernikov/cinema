package ru.films.utils.mapper;

import org.mapstruct.Mapper;
import ru.films.model.Backdrop;
import ru.films.model.dto.BackdropDto;

@Mapper(componentModel = "spring")
public interface BackdropMapper {
    BackdropDto backdropToBackdropDto(Backdrop backdrop);
}
