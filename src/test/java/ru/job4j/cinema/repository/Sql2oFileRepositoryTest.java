package ru.job4j.cinema.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.cinema.model.File;
import ru.job4j.cinema.repository.sql2o.Sql2oFileRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class Sql2oFileRepositoryTest {

    private final Sql2oFileRepository sql2oFileRepository;

    @Autowired
    Sql2oFileRepositoryTest(Sql2oFileRepository sql2oFileRepository) {
        this.sql2oFileRepository = sql2oFileRepository;
    }

    /**
     * Метод используется для проверки корректности поиска файла по id в БД (сравниваются файлы)
     */
    @Test
    public void whenFindFileByIdIsSuccess() {
        var expectedFile = new File("avatar_the_way_of_water", "src/main/resources/static/posters/avatar_the_way_of_water.jpeg");
        expectedFile.setId(1);
        var actualFile = sql2oFileRepository.findById(1);
        assertThat(actualFile).isPresent();
        assertThat(actualFile.get()).usingRecursiveComparison().isEqualTo(expectedFile);
    }

    /**
     * Метод используется для проверки корректности поиска файла по id в БД
     * (не находит файл, сравнивается пустое значение)
     */
    @Test
    public void whenFindFileByIdIsFailed() {
        var actual = sql2oFileRepository.findById(100);
        assertThat(actual).isEmpty();
    }
}