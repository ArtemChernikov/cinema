package ru.job4j.cinema.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.repository.UserRepository;

import java.util.Optional;

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
    private final PasswordEncoder passwordEncoder;

    public SimpleUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, passwordEncoder.encode(password));
    }
}
