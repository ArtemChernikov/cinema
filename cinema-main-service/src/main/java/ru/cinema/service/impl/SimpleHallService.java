package ru.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cinema.model.Hall;
import ru.cinema.repository.HallRepository;
import ru.cinema.service.HallService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
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

    @Override
    public List<Integer> getRows(Hall hall) {
        return IntStream.rangeClosed(1, hall.getRowCount()).boxed().toList();
    }

    @Override
    public List<Integer> getPlaces(Hall hall) {
        return IntStream.rangeClosed(1, hall.getPlaceCount()).boxed().toList();
    }
}