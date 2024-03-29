package ru.job4j.cinema.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.job4j.cinema.exception.RoleNotFoundException;
import ru.job4j.cinema.model.Role;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.repository.RoleRepository;

import java.util.Collection;
import java.util.List;

import static ru.job4j.cinema.utils.Constants.ROLE_NOT_FOUND;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 26.02.2024
 */
public class CustomUserDetails implements UserDetails {

    private final RoleRepository roleRepository;
    private final User user;

    public CustomUserDetails(User user, RoleRepository roleRepository) {
        this.user = user;
        this.roleRepository = roleRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = roleRepository.findById(user.getRoleId())
                .orElseThrow(() -> new RoleNotFoundException(ROLE_NOT_FOUND));
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getFullName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }
}
