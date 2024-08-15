package ru.cinema.auth.utils.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.cinema.auth.dto.RoleDto;
import ru.cinema.auth.exception.NotFoundException;
import ru.cinema.auth.model.Role;
import ru.cinema.auth.repository.RoleRepository;

import java.util.Set;

import static ru.cinema.auth.exception.message.RoleExceptionMessage.ROLE_NOT_FOUND;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.08.2024
 */
@Mapper(componentModel = "spring")
public abstract class RoleMapper {

    @Autowired
    protected RoleRepository roleRepository;

    public Role roleDtoToRole(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }
        return roleRepository.findByName(roleDto.getName()).orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));
    }

    public abstract Set<Role> roleDtosToRoles(Set<RoleDto> roleDtos);
}
