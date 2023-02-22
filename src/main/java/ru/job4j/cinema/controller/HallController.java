package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.HallService;
import ru.job4j.cinema.model.Hall;

/**
 * Класс-контроллер для работы с кинозалами {@link Hall}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 22.02.2023
 */
@ThreadSafe
@Controller
@RequestMapping("/halls")
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    /**
     * Метод используется для отображения всех кинозалов {@link Hall}
     *
     * @param model - модель для добавления кинозалов на страницу
     * @return - возвращает отображение со всеми кинозалами
     */
    @GetMapping
    public String getHalls(Model model) {
        var halls = hallService.getAllHalls();
        model.addAttribute("halls", halls);
        return "halls/list";
    }
}
