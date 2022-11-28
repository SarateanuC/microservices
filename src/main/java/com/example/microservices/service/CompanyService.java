package com.example.microservices.service;

import com.example.microservices.dto.CompanyResponseDto;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    List<CompanyResponseDto> selectAllCompanies();

    CompanyResponseDto selectCompanyById(UUID id);
}
