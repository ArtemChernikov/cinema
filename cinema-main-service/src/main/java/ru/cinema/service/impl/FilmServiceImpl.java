package ru.cinema.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.cinema.client.FilmClient;
import ru.cinema.model.dto.response.BackdropDto;
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

    @Value("${default.backdrop.url.path}")
    private String defaultBackdropUrl;

    public Collection<FilmDto> getAllFilms() {
        return objectMapper.convertValue(filmClient.getAllFilms().getBody(), new TypeReference<>() {
        });
    }

    public Optional<FilmDto> getFilmById(Long id) {
        FilmDto filmDto = objectMapper.convertValue(filmClient.getFilmById(id).getBody(), FilmDto.class);
        if (filmDto.getBackdrop() == null || filmDto.getBackdrop().getUrl() == null) {
            filmDto.setBackdrop(new BackdropDto(defaultBackdropUrl, defaultBackdropUrl));
        }
        return Optional.of(filmDto);
    }
}
