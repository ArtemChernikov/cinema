package ru.films.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.films.service.CollectionService;
import ru.films.service.FilmService;

@WebMvcTest(FilmController.class)
class FilmControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService filmService;

    @MockBean
    private CollectionService collectionService;

//    @Test
//    @SneakyThrows
//    void getBookings_whenFromNotValid_thenReturn400() {
//        mockMvc.perform(get("/bookings")
//                        .header("X-Sharer-User-Id", 1L)
//                        .param("from", "-10")
//                        .contentType("application/json")
//                        .characterEncoding(StandardCharsets.UTF_8)
//                        .content(objectMapper.writeValueAsString(bookingRequestDto)))
//                .andExpect(status().isBadRequest());
//    }

}