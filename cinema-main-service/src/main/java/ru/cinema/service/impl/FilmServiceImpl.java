package ru.cinema.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cinema.client.FilmClient;
import ru.cinema.model.dto.response.FilmDto;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 08.05.2024
 */
@RequiredArgsConstructor
@Service
public class FilmServiceImpl {

    private final FilmClient filmClient;
    private final ObjectMapper objectMapper;

    public Collection<FilmDto> getAllFilms() {
        return objectMapper.convertValue(filmClient.getAllFilms().getBody(), new TypeReference<>() {
        });
    }

    public Optional<FilmDto> getFilmById(Long id) {
        return Optional.of(objectMapper.convertValue(filmClient.getFilmById(id).getBody(), FilmDto.class));
    }
}
