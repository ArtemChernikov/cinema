package ru.cinema.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.cinema.exception.RoleNotFoundException;
import ru.cinema.model.Role;
import ru.cinema.model.User;
import ru.cinema.repository.RoleRepository;
import ru.cinema.utils.Constants;

import java.util.Collection;
import java.util.List;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 26.02.2024
 */
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final User user;
    private final RoleRepository roleRepository;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = roleRepository.findById(user.getRole().getId())
                .orElseThrow(() -> new RoleNotFoundException(Constants.ROLE_NOT_FOUND));
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
