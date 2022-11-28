package com.example.microservices.dto;

import com.example.microservices.dbo.EmployeeDbo;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponseDto {
    private UUID company_id;
    private String name;
    private List<EmployeeDbo> employeeDboList;
}
