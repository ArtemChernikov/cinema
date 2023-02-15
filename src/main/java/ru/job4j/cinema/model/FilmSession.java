package ru.job4j.cinema.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * Класс описывает модель киносеанса в кинотеатре
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 09.02.2023
 */
public class FilmSession {
    /**
     * Поле используется для маппинга модели где ключи это названия
     * столбцов в БД, а значения это названия полей модели
     */
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "film_id", "filmId",
            "hall_id", "hallId",
            "start_time", "startTime",
            "end_time", "endTime"
    );

    private int id;

    private int filmId;

    private int hallId;
    /**
     * Поле начало киносеанса
     */
    private LocalDateTime startTime;
    /**
     * Поле конец киносеанса
     */
    private LocalDateTime endTime;

    /**
     * Поле цена за киносеанс
     */
    private int price;

    public FilmSession(int filmId, int hallId, LocalDateTime startTime, LocalDateTime endTime, int price) {
        this.filmId = filmId;
        this.hallId = hallId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FilmSession that = (FilmSession) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
