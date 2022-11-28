package com.example.microservices.controller;

import com.example.microservices.dto.EmployeeResponseDto;
import com.example.microservices.service.impl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    @GetMapping("/all")
    public List<EmployeeResponseDto> getAllEmployees(){
        return employeeService.selectAllEmployees();
    }

    @GetMapping("/by-id")
    public EmployeeResponseDto getAllEmployees(UUID id){
        return employeeService.selectEmployeeById(id);
    }
}
