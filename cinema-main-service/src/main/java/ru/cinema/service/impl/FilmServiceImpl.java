package ru.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.cinema.client.FilmServiceApiClient;
import ru.cinema.model.dto.RequestAddFilms;
import ru.cinema.model.dto.response.CollectionDto;
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
    private final KafkaTemplate<String, RequestAddFilms> kafkaTemplate;

    @Override
    public List<FilmDto> getAllFilms() {
        return filmServiceApiClient.getAllFilms();
    }

    @Override
    public FilmDto getFilmById(Long id) {
        return filmServiceApiClient.getFilmById(id);
    }

    @Override
    public void addFilms(RequestAddFilms requestAddFilms) {
        kafkaTemplate.send("films", requestAddFilms);
    }

    @Override
    public List<CollectionDto> getAllCollections() {
        return filmServiceApiClient.getAllCollections();
    }
}
