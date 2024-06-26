package ru.films.service;

import ru.films.model.dto.CollectionDto;

import java.util.List;

public interface CollectionService {
    List<CollectionDto> getAll();
}
