package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.UserService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserControllerTest {

    private UserService userService;

    private UserController userController;

    @BeforeEach
    public void initService() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    /**
     * Метод используется для проверки корректной работы контроллера по выводу страницы регистрации в отображение
     */
    @Test
    public void whenRequestRegisterThenGetRegisterPage() {
        var view = userController.getRegistrationPage();
        assertThat(view).isEqualTo("users/registration");
    }

    /**
     * Метод используется для проверки корректной работы контроллера при успешной регистрации нового пользователя
     */
    @Test
    public void whenResponseRegisterThenGetLoginPage() {
        var user = new User("Artem Chernikov", "qwerty@yandex.ru", "12345");
        var userArgumentCapture = ArgumentCaptor.forClass(User.class);
        when(userService.save(userArgumentCapture.capture())).thenReturn(Optional.of(user));

        var model = new ConcurrentModel();
        var view = userController.register(model, user);
        var actualUser = userArgumentCapture.getValue();

        assertThat(view).isEqualTo("redirect:/users/login");
        assertThat(actualUser).usingRecursiveComparison().isEqualTo(user);
    }

    /**
     * Метод используется для проверки корректной работы контроллера
     * при ошибке регистрации нового пользователя (данная почта уже зарегистрирована)
     */
    @Test
    public void whenResponseRegisterThenGetErrorPage() {
        var user = new User("Artem Chernikov", "qwerty@yandex.ru", "12345");
        var userArgumentCapture = ArgumentCaptor.forClass(User.class);
        var expectedMessage = "Пользователь с данной почтой уже существует.";
        when(userService.save(userArgumentCapture.capture())).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var view = userController.register(model, user);
        var actualUser = userArgumentCapture.getValue();
        var actualMessage = model.getAttribute("message");

        assertThat(view).isEqualTo("errors/404");
        assertThat(actualMessage).isEqualTo(expectedMessage);
        assertThat(actualUser).usingRecursiveComparison().isEqualTo(user);
    }

    /**
     * Метод используется для проверки корректной работы контроллера по выводу страницы с авторизацией в отображение
     */
    @Test
    public void whenRequestLoginPageThenGetLoginPage() {
        var view = userController.getLoginPage();
        assertThat(view).isEqualTo("users/login");
    }

    /**
     * Метод используется для проверки корректной работы контроллера
     * по авторизации пользователя (ошибка авторизации)
     */
    @Test
    public void whenResponseLoginThenGetErrorAndGetLoginPage() {
        var expectedMessage = "Почта или пароль введены неверно";

        var model = new ConcurrentModel();
        var view = userController.getLoginError(model);
        var actualMessage = model.getAttribute("error");

        assertThat(view).isEqualTo("users/login");
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}