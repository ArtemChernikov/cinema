package ru.cinema.controller;

import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 17.02.2023
 */
@ThreadSafe
@Slf4j
@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String getIndex() {
        log.info("cinema-main-service: выполнение перехода на домашнюю страницу");
        return "index";
    }
}
