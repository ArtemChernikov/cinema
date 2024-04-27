package ru.cinema.controller;

import lombok.RequiredArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cinema.model.User;
import ru.cinema.service.UserService;

import java.util.Optional;

/**
 * Класс-контроллер для работы с пользователями {@link User}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 26.02.2023
 */
@ThreadSafe
@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Метод используется для вывода в отображение страницы регистрации нового пользователя {@link User}
     *
     * @return - возвращает страницу с регистрацией нового пользователя
     */
    @GetMapping("/register")
    public String getRegistrationPage() {
        return "users/registration";
    }

    /**
     * Метод используется для регистрации нового пользователя {@link User},
     * регистрация проходит успешно, если в БД нет пользователей с такой почтой, иначе будет уведомление об ошибке
     *
     * @param model - модель для отображения
     * @param user  - новый пользователь
     * @return - перенаправляет на главную страницу
     */
    @PostMapping("/register")
    public String register(Model model, @ModelAttribute User user) {
        Optional<User> newUser = userService.save(user);
        if (newUser.isEmpty()) {
            model.addAttribute("message", "Пользователь с данным логином уже существует.");
            return "errors/404";
        }
        return "redirect:/users/login";
    }

    /**
     * Метод используется для вывода в отображение страницы с авторизацией пользователя {@link User}
     *
     * @return - возвращает страницу с авторизацией
     */
    @GetMapping("/login")
    public String getLoginPage() {
        return "users/login";
    }

    /**
     * Метод используется для вывода на страницу сообщения об ошибке
     *
     * @param model - модель
     * @return - перенаправляет на страницу авторизации
     */
    @GetMapping("/login-error")
    public String getLoginError(Model model) {
        model.addAttribute("error", "Логин или пароль введены неверно");
        return "users/login";
    }
}
