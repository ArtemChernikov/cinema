package ru.films.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.films.model.dto.BackdropDto;
import ru.films.model.dto.CollectionDto;
import ru.films.model.dto.FilmDto;
import ru.films.model.dto.PosterDto;
import ru.films.model.dto.RatingDto;
import ru.films.repository.CountryRepository;
import ru.films.repository.FilmRepository;
import ru.films.repository.GenreRepository;
import ru.films.service.CountryService;
import ru.films.service.FilmService;
import ru.films.service.GenreService;
import utils.IntegrationTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FilmControllerIntegrationTest extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private FilmService filmService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private GenreRepository genreRepository;

    private FilmDto filmDto1;

    private FilmDto filmDto2;

    private CollectionDto collectionDto1;

    private CollectionDto collectionDto2;

    @BeforeEach
    void init() {
        RatingDto rating1 = new RatingDto(7.8, 8.2, 6.5, 5.9);
        PosterDto poster1 = new PosterDto("poster1_url", "poster1_preview_url");
        BackdropDto backdrop1 = new BackdropDto("backdrop1_url", "backdrop1_preview_url");

        RatingDto rating2 = new RatingDto(6.5, 7.0, 5.0, 4.5);
        PosterDto poster2 = new PosterDto("poster2_url", "poster2_preview_url");
        BackdropDto backdrop2 = new BackdropDto("backdrop2_url", "backdrop2_preview_url");

        Set<String> genres1 = new HashSet<>(Arrays.asList("Action", "Drama"));
        Set<String> countries1 = new HashSet<>(Arrays.asList("USA", "UK"));

        Set<String> genres2 = new HashSet<>(Arrays.asList("Comedy", "Romance"));
        Set<String> countries2 = new HashSet<>(Arrays.asList("France", "Germany"));

        filmDto1 = new FilmDto(1L, "Film 1", "Alt Film 1", "Movie", 2020,
                "Description of Film 1", "Short description of Film 1", rating1, 120,
                16, poster1, backdrop1, genres1, countries1
        );
        filmDto2 = new FilmDto(2L, "Film 2", "Alt Film 2", "Series", 2018,
                "Description of Film 2", "Short description of Film 2", rating2, 90,
                12, poster2, backdrop2, genres2, countries2
        );

        collectionDto1 = new CollectionDto("Action Movies", "action_movies");
        collectionDto2 = new CollectionDto("Comedy Films", "comedy_films");
    }

    @AfterEach
    void clearDatabase() {
//        genreRepository.deleteAllInBatch();
//        countryRepository.deleteAllInBatch();
//        filmRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("Тест на добавление фильмов")
    @SneakyThrows
    void whenAddPopularFilms() {
        mockMvc.perform(post("/films/add-popular-films"))
                .andExpect(status().isCreated());
    }

//    @DisplayName("Изменение сотрудника успешно")
//    @Test
//    @SneakyThrows
//    void editEmployeeIsSuccess() {
//        Position position = new Position("Manager");
//        Department department = new Department("Sales");
//        Employee employee = new Employee("Vladimir", 4000, position, department);
//        positionRepository.save(position);
//        departmentRepository.save(department);
//        employeeRepository.save(employee);
//        Long employeeId = employee.getId();
//        EmployeeDTO employeeDtoForUpdate = new EmployeeDTO("Andrey", 2000, "Manager", "Sales");
//
//        String jsonEmployee = new ObjectMapper().writeValueAsString(employeeDtoForUpdate);
//
//        mockMvc.perform(put("/employees/{id}", employeeId)
//                        .with(user("user_admin").roles("ADMIN"))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonEmployee))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.name").value("Andrey"))
//                .andExpect(jsonPath("$.salary").value(2000))
//                .andExpect(jsonPath("$.positionName").value("Manager"))
//                .andExpect(jsonPath("$.departmentName").value("Sales"));
//    }

}