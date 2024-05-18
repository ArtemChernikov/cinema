package ru.cinema.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.cinema.model.Ticket;
import ru.cinema.model.dto.TicketDto;

@Mapper(componentModel = "spring", uses = {FilmSessionMapper.class, UserMapper.class})
public interface TicketMapper {

    @Mapping(target = "filmSession", source = "filmSessionId", qualifiedByName = "createFilmSessionWithId")
    @Mapping(target = "user", source = "userId", qualifiedByName = "createUserWithId")
    Ticket ticketDtoToTicket(TicketDto ticketDto);

}
