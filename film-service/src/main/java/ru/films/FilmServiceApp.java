package ru.films;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 08.02.2023
 */
@SpringBootApplication
@EnableFeignClients
public class FilmServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(FilmServiceApp.class, args);
    }
}
