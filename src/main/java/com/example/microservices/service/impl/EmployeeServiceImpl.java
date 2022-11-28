package com.example.microservices.service.impl;

import com.example.microservices.dbo.EmployeeDbo;
import com.example.microservices.dto.EmployeeResponseDto;
import com.example.microservices.mapper.EmployeeMapper;
import com.example.microservices.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeResponseDto> selectAllEmployees() {
        return employeeMapper.selectAllEmployees().stream()
                .map(this::convertToDto)
                .collect(toList());
    }

    @Override
    public EmployeeResponseDto selectEmployeeById(UUID id) {
        return convertToDto(employeeMapper.selectEmployeeById(id).orElseThrow());
    }

    private EmployeeResponseDto convertToDto(EmployeeDbo employeeDbo) {
        return EmployeeResponseDto.builder()
                .address(employeeDbo.getAddress())
                .status(employeeDbo.getStatus())
                .name(employeeDbo.getName())
                .companyDbo(employeeDbo.getCompanyDbo())
                .id(employeeDbo.getId())
                .build();
    }
}
