package ru.job4j.cinema.service;

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
@Service
public class SimpleUserService implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public SimpleUserService(UserRepository userRepository, RoleRepository roleRepository,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> save(User user) {
        Role role = roleRepository.findByName(Constants.ROLE_USER)
                .orElseThrow(() -> new RoleNotFoundException(ROLE_NOT_FOUND));
        user.setRoleId(role.getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, passwordEncoder.encode(password));
    }
}
