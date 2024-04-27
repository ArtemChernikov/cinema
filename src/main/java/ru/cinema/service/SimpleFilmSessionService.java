package ru.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cinema.dto.FilmSessionDto;
import ru.cinema.model.Film;
import ru.cinema.model.FilmSession;
import ru.cinema.model.Hall;
import ru.cinema.repository.FilmRepository;
import ru.cinema.repository.FilmSessionRepository;
import ru.cinema.repository.HallRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Класс-сервис для работы с киносеансами {@link FilmSession} и DTO {@link FilmSessionDto}
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

    /**
     * Метод используется для получения киносеанса {@link FilmSession}
     * из репозитория {@link FilmSessionRepository} по id и дальнейшего его преобразования в DTO {@link FilmSessionDto}
     *
     * @param id - id киносеанса
     * @return - возвращает DTO {@link FilmSessionDto} обернутый в {@link Optional}
     */
    @Override
    public Optional<FilmSessionDto> getFilmSessionById(int id) {
        var optionalFilmSession = filmSessionRepository.findById(id);
        if (optionalFilmSession.isEmpty()) {
            return Optional.empty();
        }
        var filmSession = optionalFilmSession.get();
        return Optional.of(convert(filmSession));
    }

    /**
     * Метод используется для получения всех киносеансов {@link FilmSession} из репозитория {@link FilmSessionRepository}
     * и дальнейшего их преобразования в коллекцию DTO {@link FilmSessionDto}
     *
     * @return - возвращает коллецию всех киносеансов в DTO {@link FilmSessionDto} виде
     */
    @Override
    public Collection<FilmSessionDto> getAllFilmSessions() {
        var filmSessions = filmSessionRepository.findAll();
        return filmSessions.stream().map(this::convert).toList();
    }

    /**
     * Метод используется для получения названия фильма {@link Film}
     * киносеанса {@link FilmSession} из репозитория {@link FilmRepository}
     *
     * @param filmSession - киносеанс
     * @return - возвращает название фильма
     */
    private String getFilm(FilmSession filmSession) {
        var optionalFilm = filmRepository.findById(filmSession.getFilm().getId());
        return optionalFilm.isPresent() ? optionalFilm.get().getName() : "Неизвестный фильм";
    }

    /**
     * Метод используется для получения названия зала {@link Hall}
     * киносеанса {@link FilmSession} из репозитория {@link HallRepository}
     *
     * @param filmSession - киносеанс
     * @return - возвращает название зала
     */
    private String getHall(FilmSession filmSession) {
        var optionalHall = hallRepository.findById(filmSession.getHall().getId());
        return optionalHall.isPresent() ? optionalHall.get().getName() : "Неизвестный кинозал";
    }

    /**
     * Метод используется для преобразования киносеанса {@link FilmSession} в DTO {@link FilmSessionDto}
     *
     * @param filmSession - киносеанс
     * @return - возвращает DTO {@link FilmSessionDto}
     */
    private FilmSessionDto convert(FilmSession filmSession) {
        return new FilmSessionDto(filmSession.getId(), getFilm(filmSession), getHall(filmSession),
                filmSession.getHall().getId(), filmSession.getStartTime(), filmSession.getEndTime(), filmSession.getPrice());
    }
}
