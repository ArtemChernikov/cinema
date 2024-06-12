package ru.films.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 30.04.2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KinopoiskApiResponse {
    private List<Document> docs;
    private int total;
    private int limit;
    private int page;
    private int pages;
}