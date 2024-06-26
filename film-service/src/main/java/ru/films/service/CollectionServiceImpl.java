package ru.films.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.films.model.dto.CollectionDto;
import ru.films.repository.CollectionRepository;
import ru.films.utils.mapper.CollectionMapper;

import java.util.List;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 26.06.2024
 */
@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;
    private final CollectionMapper collectionMapper;

    @Override
    public List<CollectionDto> getAll() {
        return collectionMapper.listCollectionToListCollectionDto(collectionRepository.findAll());
    }
}
