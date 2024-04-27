package ru.cinema.controller;

import lombok.RequiredArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cinema.service.HallService;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 22.02.2023
 */
@ThreadSafe
@RequiredArgsConstructor
@Controller
@RequestMapping("/halls")
public class HallController {

    private final HallService hallService;

    @GetMapping
    public String getHalls(Model model) {
        var halls = hallService.getAllHalls();
        model.addAttribute("halls", halls);
        return "halls/list";
    }
}
