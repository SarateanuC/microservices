package com.example.microservices.dbo;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDbo {
    private UUID company_id;
    private String name;
    private List<EmployeeDbo> employeeDboList;
}
