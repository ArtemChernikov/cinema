package ru.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cinema.model.Hall;
import ru.cinema.repository.HallRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Класс-сервис для работы с кинозалами {@link Hall}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.02.2023
 */
@RequiredArgsConstructor
@Service
public class SimpleHallService implements HallService {

    private final HallRepository hallRepository;

    @Override
    public Optional<Hall> getHallById(int id) {
        return hallRepository.findById(id);
    }

    @Override
    public Collection<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    /**
     * Метод используется для получения всех рядов в зале в виде списка
     *
     * @param hall - зал
     * @return - список рядов
     */
    @Override
    public List<Integer> getRows(Hall hall) {
        return IntStream.rangeClosed(1, hall.getRowCount()).boxed().toList();
    }

    /**
     * Метод используется для получения всех мест в ряду в зале в виде списка
     *
     * @param hall - зал
     * @return - список мест в ряду
     */
    @Override
    public List<Integer> getPlaces(Hall hall) {
        return IntStream.rangeClosed(1, hall.getPlaceCount()).boxed().toList();
    }
}
