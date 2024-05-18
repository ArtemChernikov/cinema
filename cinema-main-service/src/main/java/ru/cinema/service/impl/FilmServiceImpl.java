package ru.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.cinema.client.FilmClient;
import ru.cinema.model.dto.response.BackdropDto;
import ru.cinema.model.dto.response.FilmDto;
import ru.cinema.service.FilmService;

import java.util.List;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 08.05.2024
 */
@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {

    private final FilmClient filmClient;

    @Value("${default.backdrop.url.path}")
    private String defaultBackdropUrl;

    public List<FilmDto> getAllFilms() {
        return filmClient.getAllFilms();
    }

    public FilmDto getFilmById(Long id) {
        FilmDto filmDto = filmClient.getFilmById(id);
        if (filmDto.getBackdrop() == null || filmDto.getBackdrop().getUrl() == null) {
            filmDto.setBackdrop(new BackdropDto(defaultBackdropUrl, defaultBackdropUrl));
        }
        return filmDto;
    }
}
