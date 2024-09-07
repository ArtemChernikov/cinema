package ru.films.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class FilmControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

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