package com.example.microservices.companyTests;

import com.example.microservices.dto.CompanyResponseDto;
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
                "/sql/20221128_create-company.sql",
                "/sql/20221128_insert-company.sql",
                "/sql/20221128_create- employee.sql",
                "/sql/20221128_insert-employee.sql"
        }))
public class UniversityIntegrationTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void selectById() {
        MvcResult mvcResult = mockMvc.perform(get("/api/university/by-id")
                        .param("id", "ab3daa2c-6d93-11ed-a1eb-0242ac120002")
                        .contentType("application/json"))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        CompanyResponseDto company = objectMapper.readValue(contentAsString, CompanyResponseDto.class);
        assertThat(company.getName()).isEqualTo("Endava");
//        assertThat(company.getCourses().size()).isEqualTo(1);
//        assertThat(company.getStudents().size()).isEqualTo(1);
    }

    @Test
    @SneakyThrows
    void selectAll() {
        MvcResult mvcResult = mockMvc.perform(get("/api/university/all")
                        .contentType("application/json"))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        List<CompanyResponseDto> universityDtos = List.of(objectMapper.readValue(contentAsString, CompanyResponseDto[].class));
        assertThat(universityDtos.size()).isEqualTo(2);
    }
}
