package ru.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cinema.client.FilmServiceApiClient;
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

    private final FilmServiceApiClient filmServiceApiClient;

    public List<FilmDto> getAllFilms() {
        return filmServiceApiClient.getAllFilms();
    }

    public FilmDto getFilmById(Long id) {
        return filmServiceApiClient.getFilmById(id);
    }
}
