package ru.cinema.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cinema.model.User;
import ru.cinema.service.UserService;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 26.02.2023
 */
@ThreadSafe
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String getRegistrationPage() {
        log.info("cinema-main-service: выполнение запроса для перехода на страницу регистрации пользователя");
        return "users/registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        log.info("cinema-main-service: выполнение запроса на регистрацию (создание) нового пользователя: {}", user);
        userService.save(user);
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        log.info("cinema-main-service: выполнение запроса для перехода на страницу автоизации пользователя");
        return "users/login";
    }

    @GetMapping("/login-error")
    public String getLoginError(Model model) {
        log.info("cinema-main-service: логин или пароль введены неверно");
        model.addAttribute("error", "Логин или пароль введены неверно");
        return "users/login";
    }
}
