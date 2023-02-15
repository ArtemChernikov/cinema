package ru.job4j.cinema.dto;

import ru.job4j.cinema.model.FilmSession;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) для класса {@link FilmSession}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
public class FilmSessionDto {

    private String film;

    private String hall;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int price;

    public FilmSessionDto(String film, String hall, LocalDateTime startTime, LocalDateTime endTime, int price) {
        this.film = film;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
