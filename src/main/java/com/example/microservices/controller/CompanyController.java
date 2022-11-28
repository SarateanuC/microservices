package com.example.microservices.controller;

import com.example.microservices.dto.CompanyResponseDto;
import com.example.microservices.service.impl.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyServiceImpl companyService;

    @GetMapping("/all")
    public List<CompanyResponseDto> getAllCompanies() {
        return companyService.selectAllCompanies();
    }

    @GetMapping("/by-id")
    public CompanyResponseDto getCompanyById(UUID id) {
        return companyService.selectCompanyById(id);
    }
}
