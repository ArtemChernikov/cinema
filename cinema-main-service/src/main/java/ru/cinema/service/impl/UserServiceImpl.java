package ru.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.cinema.exception.NotFoundException;
import ru.cinema.exception.UserExistsException;
import ru.cinema.model.Role;
import ru.cinema.model.User;
import ru.cinema.repository.RoleRepository;
import ru.cinema.repository.UserRepository;
import ru.cinema.service.UserService;
import ru.cinema.utils.Constants;

import java.util.Optional;

import static ru.cinema.exception.message.RoleExceptionMessage.ROLE_NOT_FOUND;
import static ru.cinema.exception.message.UserExceptionMessage.USERNAME_ALREADY_EXISTS;

/**
 * @author Artem Chernikov
 * @version 1.1
 * @since 26.02.2023
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserExistsException(USERNAME_ALREADY_EXISTS);
        }
        Role role = roleRepository.findByName(Constants.ROLE_USER)
                .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, passwordEncoder.encode(password));
    }
}
