package ru.films.utils.mapper;

import org.mapstruct.Mapper;
import ru.films.model.Collection;
import ru.films.model.dto.CollectionDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollectionMapper {
    CollectionDto collectionToCollectionDto(Collection collection);

    List<CollectionDto> listCollectionToListCollectionDto(List<Collection> collections);
}
