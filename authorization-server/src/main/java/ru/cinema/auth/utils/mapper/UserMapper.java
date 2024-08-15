package ru.cinema.auth.utils.mapper;

import org.mapstruct.Mapper;
import ru.cinema.auth.dto.UserDto;
import ru.cinema.auth.model.User;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    User userDtoToUser(UserDto userDto);

}
