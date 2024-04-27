package ru.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Класс-контроллер главной страницы веб-приложения
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 17.02.2023
 */
@ThreadSafe
@Controller
public class IndexController {

    /**
     * Метод используется для отображения главной страницы веб-приложения
     *
     * @return - возвращает главную страницу
     */
    @GetMapping({"/", "/index"})
    public String getIndex() {
        return "index";
    }
}
