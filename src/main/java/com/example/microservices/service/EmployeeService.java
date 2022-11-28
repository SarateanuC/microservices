package com.example.microservices.service;

import com.example.microservices.dto.EmployeeResponseDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    List<EmployeeResponseDto> selectAllEmployees();

    EmployeeResponseDto selectEmployeeById(UUID id);
}
