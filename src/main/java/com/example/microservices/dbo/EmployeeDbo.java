package com.example.microservices.dbo;

import com.example.microservices.dbo.enums.EmployeeStatus;
import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDbo {
    private UUID id;
    private String name;
    private CompanyDbo companyDbo;
    private Address address;
    private EmployeeStatus status;
}
