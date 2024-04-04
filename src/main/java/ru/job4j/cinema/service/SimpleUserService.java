package ru.job4j.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.exception.RoleNotFoundException;
import ru.job4j.cinema.model.Role;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.repository.RoleRepository;
import ru.job4j.cinema.repository.UserRepository;
import ru.job4j.cinema.utils.Constants;

import java.util.Optional;

import static ru.job4j.cinema.utils.Constants.ROLE_NOT_FOUND;

/**
 * Класс-сервис для работы с пользователями {@link User}
 *
 * @author Artem Chernikov
 * @version 1.1
 * @since 26.02.2023
 */
@RequiredArgsConstructor
@Service
public class SimpleUserService implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> save(User user) {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            return Optional.empty();
        }
        Role role = roleRepository.findByName(Constants.ROLE_USER)
                .orElseThrow(() -> new RoleNotFoundException(ROLE_NOT_FOUND));
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> getById(int id) {
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
