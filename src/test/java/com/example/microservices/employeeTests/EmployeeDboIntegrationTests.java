package com.example.microservices.employeeTests;

import com.example.microservices.dto.EmployeeResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@SqlGroup(
        @Sql(scripts = {"/sql/20221128_create-address.sql",
                "/sql/20221128_insert-address.sql",
                "/sql/20221125_create-company.sql",
                "/sql/20221125_insert_company.sql",
                "/sql/20221125_create-employee.sql",
                "/sql/20221125_insert-employee.sql"
        }))
class EmployeeDboIntegrationTests {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void selectStudentById() {
        MvcResult mvcResult = mockMvc.perform(get("/api/employee/by-id")
                        .param("student-id", "a")
                        .contentType("application/json"))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        EmployeeResponseDto studentDto = objectMapper.readValue(contentAsString, EmployeeResponseDto.class);
    }

    @Test
    @SneakyThrows
    void selectAll() {
        MvcResult mvcResult = mockMvc.perform(get("/api/employee/all")
                        .contentType("application/json"))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        List<EmployeeResponseDto> studentDtos = List.of(objectMapper.readValue(contentAsString, EmployeeResponseDto[].class));
        assertThat(studentDtos.size()).isEqualTo(4);
    }

}
