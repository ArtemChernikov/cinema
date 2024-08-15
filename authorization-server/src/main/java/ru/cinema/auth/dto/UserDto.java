package ru.cinema.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.08.2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    private String name;

    private String surname;

    private String email;

    private String username;

    private String password;

    private Set<RoleDto> roles;

}
