package ru.job4j.cinema.model;

import java.util.Objects;

/**
 * Класс описывает модель жанра фильма
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 09.02.2023
 */
public class Genre {

    private int id;

    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Genre genre = (Genre) o;
        return id == genre.id && Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
