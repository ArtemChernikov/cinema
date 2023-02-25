package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.FilmSessionRepository;
import ru.job4j.cinema.repository.HallRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Класс-сервис для работы с киносеансами {@link FilmSession} и DTO {@link FilmSessionDto}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
@Service
public class SimpleFilmSessionService implements FilmSessionService {

    private final FilmSessionRepository filmSessionRepository;

    private final FilmRepository filmRepository;

    private final HallRepository hallRepository;

    public SimpleFilmSessionService(FilmSessionRepository filmSessionRepository,
                                    FilmRepository filmRepository, HallRepository hallRepository) {
        this.filmSessionRepository = filmSessionRepository;
        this.filmRepository = filmRepository;
        this.hallRepository = hallRepository;
    }

    /**
     * Метод используется для получения названия фильма {@link Film}
     * киносеанса {@link FilmSession} из репозитория {@link FilmRepository}
     *
     * @param filmSession - киносеанс
     * @return - возвращает название фильма
     */
    private String getFilm(FilmSession filmSession) {
        var optionalFilm = filmRepository.findById(filmSession.getFilmId());
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
        var optionalHall = hallRepository.findById(filmSession.getHallId());
        return optionalHall.isPresent() ? optionalHall.get().getName() : "Неизвестный кинозал";
    }

    /**
     * Метод используется для преобразования киносеанса {@link FilmSession} в DTO {@link FilmSessionDto}
     *
     * @param filmSession - киносеанс
     * @return - возвращает DTO {@link FilmSessionDto}
     */
    private FilmSessionDto convert(FilmSession filmSession) {
        return new FilmSessionDto(filmSession.getId(), getFilm(filmSession), getHall(filmSession), filmSession.getHallId(),
                filmSession.getStartTime(), filmSession.getEndTime(), filmSession.getPrice());
    }

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
        return filmSessions.stream().map(this::convert).collect(Collectors.toList());
    }
}
