package ru.job4j.cinema.dto;

import ru.job4j.cinema.model.File;

/**
 * DTO (Data Transfer Object) для класса {@link File}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 14.02.2023
 */
public class FileDto {

    private String name;

    private byte[] content;

    public FileDto(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
