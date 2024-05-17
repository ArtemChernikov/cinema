package ru.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cinema.exception.NotCorrectDateTimeException;
import ru.cinema.model.FilmSession;
import ru.cinema.model.Hall;
import ru.cinema.model.dto.FilmSessionCreateDto;
import ru.cinema.model.dto.FilmSessionDto;
import ru.cinema.repository.FilmRepository;
import ru.cinema.repository.FilmSessionRepository;
import ru.cinema.repository.HallRepository;
import ru.cinema.service.FilmSessionService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import static ru.cinema.exception.message.FilmSessionExceptionMessage.END_TIME_BEFORE_START_TIME;
import static ru.cinema.exception.message.FilmSessionExceptionMessage.START_END_BEFORE_CURRENT;

/**
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
    public void createFilmSession(FilmSessionCreateDto filmSessionCreateDto) {
        checkStartAndEndTime(filmSessionCreateDto.getStartTime(), filmSessionCreateDto.getEndTime());
        Hall hall = Hall.builder().id(filmSessionCreateDto.getHallId()).build();
        FilmSession newFilmSession = FilmSession.builder()
                .hall(hall)
                .filmId(filmSessionCreateDto.getFilmId())
                .price(filmSessionCreateDto.getPrice())
                .startTime(filmSessionCreateDto.getStartTime())
                .endTime(filmSessionCreateDto.getEndTime())
                .build();
        filmSessionRepository.save(newFilmSession);
    }

    @Override
    public Optional<FilmSessionDto> getFilmSessionById(long id) {
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
        var optionalFilm = filmRepository.findById(filmSession.getFilmId());
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

    private void checkStartAndEndTime(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime now = LocalDateTime.now();
        if (endTime.isBefore(startTime)) {
            throw new NotCorrectDateTimeException(END_TIME_BEFORE_START_TIME);
        }
        if (startTime.isBefore(now) || endTime.isBefore(now)) {
            throw new NotCorrectDateTimeException(START_END_BEFORE_CURRENT);
        }
    }
}
