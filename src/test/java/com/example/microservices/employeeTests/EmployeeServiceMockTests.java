package com.example.microservices.employeeTests;

import com.example.microservices.dbo.Address;
import com.example.microservices.dbo.CompanyDbo;
import com.example.microservices.dbo.EmployeeDbo;
import com.example.microservices.dbo.enums.EmployeeStatus;
import com.example.microservices.mapper.EmployeeMapper;
import com.example.microservices.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Optional.of;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceMockTests {
    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    public void selectByIdSuccessTest() {
        EmployeeDbo employeeDbo = EmployeeDbo.builder()
                .companyDbo(CompanyDbo.builder().name("test").build())
                .id(UUID.randomUUID())
                .address(Address.builder().name("a1").build())
                .status(EmployeeStatus.EMPLOYED)
                .name("name")
                .build();
        when(employeeMapper.selectEmployeeById(any())).thenReturn(of(employeeDbo));
        assertThat(employeeServiceImpl.selectEmployeeById(UUID.randomUUID()).getName()).isEqualTo(employeeDbo.getName());
    }

    @Test
    public void selectByIdEmptyOptionalTest() {
        when(employeeMapper.selectEmployeeById(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> employeeServiceImpl.selectEmployeeById(randomUUID()));
    }

    @Test
    public void selectByAllSuccessTest() {
        EmployeeDbo employeeDbo1 = EmployeeDbo.builder()
                .companyDbo(CompanyDbo.builder().name("test1").build())
                .id(UUID.randomUUID())
                .address(Address.builder().name("a1").build())
                .status(EmployeeStatus.EMPLOYED)
                .name("name1")
                .build();
        EmployeeDbo employeeDbo2 = EmployeeDbo.builder()
                .companyDbo(CompanyDbo.builder().name("test2").build())
                .id(UUID.randomUUID())
                .address(Address.builder().name("a2").build())
                .status(EmployeeStatus.EMPLOYED)
                .name("name2")
                .build();
        when(employeeMapper.selectAllEmployees()).thenReturn(List.of(employeeDbo2,employeeDbo1));
        assertThat(employeeServiceImpl.selectAllEmployees().get(0).getName()).isEqualTo(employeeDbo2.getName());
        assertThat(employeeServiceImpl.selectAllEmployees().size()).isEqualTo(2);

    }

    @Test
    public void selectByAllEmptyListTest() {
        when(employeeMapper.selectAllEmployees()).thenReturn(Collections.emptyList());
        assertThat(employeeServiceImpl.selectAllEmployees()).isEmpty();
    }
}
