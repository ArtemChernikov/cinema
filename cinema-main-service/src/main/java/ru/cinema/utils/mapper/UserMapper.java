package ru.cinema.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ru.cinema.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Named("createUserWithId")
    default User createUserWithId(Long id) {
        return User.builder().id(id).build();
    }
}
