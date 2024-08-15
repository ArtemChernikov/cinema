package ru.cinema.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cinema.auth.dto.UserDto;
import ru.cinema.auth.exception.UserExistsException;
import ru.cinema.auth.model.User;
import ru.cinema.auth.repository.UserRepository;

import static ru.cinema.auth.exception.message.UserExceptionMessage.USERNAME_ALREADY_EXISTS;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.08.2024
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь '%s' не найден", username)));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .toList()
        );
    }

    public User save(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UserExistsException(USERNAME_ALREADY_EXISTS);
        }


        return null;
    }
}
