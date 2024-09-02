package ru.cinema.utils.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.cinema.model.File;
import ru.cinema.model.FilmSession;
import ru.cinema.model.Hall;
import ru.cinema.model.dto.FilmSessionDto;
import ru.cinema.model.dto.HallDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FilmSessionMapperImpl.class, HallMapperImpl.class})
class FilmSessionMapperTest {

    @Autowired
    private FilmSessionMapper filmSessionMapper;

    @Test
    void whenFilmSessionToFilmSessionDto() {
        Hall hall = new Hall(1L, "hall", 2, 3, "desc", new File());
        FilmSession filmSession = new FilmSession(1L, "Film", 1L, hall, LocalDateTime.now(),
                LocalDateTime.now().plusHours(2), 500);
        HallDto hallDto = new HallDto("hall", List.of(1, 2), List.of(1, 2, 3));
        FilmSessionDto expected = new FilmSessionDto(1L, "Film", hallDto, LocalDateTime.now(),
                LocalDateTime.now().plusHours(2), 500);
        FilmSessionDto actual = filmSessionMapper.filmSessionToFilmSessionDto(filmSession);
        assertThat(actual).isEqualTo(expected);
    }
}