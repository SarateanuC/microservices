package com.example.microservices.companyTests;

import com.example.microservices.dbo.CompanyDbo;
import com.example.microservices.dbo.EmployeeDbo;
import com.example.microservices.mapper.CompanyMapper;
import com.example.microservices.service.impl.CompanyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UniversityServiceMockTest {
    @Mock
    private CompanyMapper companyMapper;

    @InjectMocks
    private CompanyServiceImpl companyServiceImpl;

    @Test
    public void selectByIdSuccessTest() {
        CompanyDbo company = CompanyDbo.builder()
                .company_id(UUID.randomUUID())
                .name("name")
                .employeeDboList(List.of(EmployeeDbo.builder().name("test").build()))
                .build();
        when(companyMapper.selectCompanyById(any())).thenReturn(Optional.ofNullable(company));
        assertThat(companyServiceImpl.selectCompanyById(UUID.randomUUID()).getName()).isEqualTo("name");
    }

    @Test
    public void selectByIdEmptyOptionalTest() {
        when(companyMapper.selectCompanyById(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> companyServiceImpl.selectCompanyById(randomUUID()));
    }

    @Test
    public void selectByAllSuccessTest() {
        CompanyDbo company1 = CompanyDbo.builder()
                .company_id(UUID.randomUUID())
                .name("name1")
                .employeeDboList(List.of(EmployeeDbo.builder().name("test1").build()))
                .build();
        CompanyDbo company2 = CompanyDbo.builder()
                .company_id(UUID.randomUUID())
                .name("name2")
                .employeeDboList(List.of(EmployeeDbo.builder().name("test2").build()))
                .build();

        when(companyMapper.selectAll()).thenReturn(List.of(company1, company2));
        assertThat(companyServiceImpl.selectAllCompanies().get(0).getName()).isEqualTo(company1.getName());
        assertThat(companyServiceImpl.selectAllCompanies().size()).isEqualTo(2);
    }

    @Test
    public void selectByAllEmptyListTest() {
        when(companyMapper.selectAll()).thenReturn(Collections.emptyList());
        assertThat(companyServiceImpl.selectAllCompanies()).isEmpty();
    }
}
