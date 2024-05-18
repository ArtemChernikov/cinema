package ru.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cinema.exception.NotCorrectDateTimeException;
import ru.cinema.model.FilmSession;
import ru.cinema.model.Hall;
import ru.cinema.model.dto.FilmSessionCreateDto;
import ru.cinema.model.dto.FilmSessionDto;
import ru.cinema.repository.FilmSessionRepository;
import ru.cinema.service.FilmSessionService;
import ru.cinema.utils.mapper.FilmSessionMapper;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
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
    private final FilmSessionMapper filmSessionMapper;

    @Override
    public void createFilmSession(FilmSessionCreateDto filmSessionCreateDto) {
        checkStartAndEndTime(filmSessionCreateDto.getStartTime(), filmSessionCreateDto.getEndTime());
        FilmSession newFilmSession = filmSessionMapper.filmSessionCreateDtoToFilmSession(filmSessionCreateDto);
        filmSessionRepository.save(newFilmSession);
    }

    @Override
    public Optional<FilmSessionDto> getFilmSessionById(long id) {
        var optionalFilmSession = filmSessionRepository.findById(id);
        if (optionalFilmSession.isEmpty()) {
            return Optional.empty();
        }
        FilmSession filmSession = optionalFilmSession.get();
        return Optional.of(filmSessionMapper.filmSessionToFilmSessionDto(filmSession));
    }

    @Override
    public Collection<FilmSessionDto> getAllFilmSessions() {
        List<FilmSession> filmSessions = filmSessionRepository.findAll();
        return filmSessionMapper.filmSessionListToFilmSessionListDto(filmSessions);
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
