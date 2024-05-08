package ru.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cinema.model.dto.FilmSessionDto;
import ru.cinema.model.FilmSession;
import ru.cinema.repository.FilmRepository;
import ru.cinema.repository.FilmSessionRepository;
import ru.cinema.repository.HallRepository;
import ru.cinema.service.FilmSessionService;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
@RequiredArgsConstructor
@Service
public class SimpleFilmSessionService implements FilmSessionService {

    private final FilmSessionRepository filmSessionRepository;

    private final FilmRepository filmRepository;

    private final HallRepository hallRepository;

    @Override
    public Optional<FilmSessionDto> getFilmSessionById(int id) {
        var optionalFilmSession = filmSessionRepository.findById(id);
        if (optionalFilmSession.isEmpty()) {
            return Optional.empty();
        }
        var filmSession = optionalFilmSession.get();
        return Optional.of(convert(filmSession));
    }

    @Override
    public Collection<FilmSessionDto> getAllFilmSessions() {
        var filmSessions = filmSessionRepository.findAll();
        return filmSessions.stream().map(this::convert).toList();
    }

    private String getFilm(FilmSession filmSession) {
        var optionalFilm = filmRepository.findById(filmSession.getFilm().getId());
        return optionalFilm.isPresent() ? optionalFilm.get().getName() : "Неизвестный фильм";
    }

    private String getHall(FilmSession filmSession) {
        var optionalHall = hallRepository.findById(filmSession.getHall().getId());
        return optionalHall.isPresent() ? optionalHall.get().getName() : "Неизвестный кинозал";
    }

    private FilmSessionDto convert(FilmSession filmSession) {
        return new FilmSessionDto(filmSession.getId(), getFilm(filmSession), getHall(filmSession),
                filmSession.getHall().getId(), filmSession.getStartTime(), filmSession.getEndTime(), filmSession.getPrice());
    }
}
