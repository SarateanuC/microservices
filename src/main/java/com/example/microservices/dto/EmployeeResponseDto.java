package com.example.microservices.dto;

import com.example.microservices.dbo.Address;
import com.example.microservices.dbo.CompanyDbo;
import com.example.microservices.dbo.enums.EmployeeStatus;
import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {
    private UUID id;
    private String name;
    private CompanyDbo companyDbo;
    private Address address;
    private EmployeeStatus status;
}
