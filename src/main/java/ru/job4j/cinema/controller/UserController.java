package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Класс-контроллер для работы с пользователями {@link User}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 26.02.2023
 */
@ThreadSafe
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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
        var newUser = userService.save(user);
        if (newUser.isEmpty()) {
            model.addAttribute("message", "Пользователь с данной почтой уже существует.");
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
     * Метод используется для авторизации пользователя {@link User}, если данные (email и password)
     * введены корректно, авторизация успешна, иначе будет уведомление об ошибке
     *
     * @param user  - пользователь
     * @param model - модель
     * @return - перенаправляет на главную страницу
     */
    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, HttpServletRequest request) {
        var userOptional = userService.getByEmailAndPassword(user.getEmail(), user.getPassword());
        if (userOptional.isEmpty()) {
            model.addAttribute("error", "Почта или пароль введены неверно");
            return "users/login";
        }
//        var newSession = request.getSession();
//        newSession.setAttribute("user", userOptional.get());
//        return "redirect:/index";
        return "users/login";
    }
}
