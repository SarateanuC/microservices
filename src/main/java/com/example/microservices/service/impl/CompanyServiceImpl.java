package com.example.microservices.service.impl;

import com.example.microservices.dbo.CompanyDbo;
import com.example.microservices.dto.CompanyResponseDto;
import com.example.microservices.mapper.CompanyMapper;
import com.example.microservices.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyResponseDto> selectAllCompanies() {
        return companyMapper.selectAll().stream().
                map(this::convertToDto)
                .collect(toList());
    }

    @Override
    public CompanyResponseDto selectCompanyById(UUID id) {
        return convertToDto(companyMapper.selectCompanyById(id).orElseThrow());
    }

    private CompanyResponseDto convertToDto(CompanyDbo companyDbo) {
        return CompanyResponseDto.builder()
                .company_id(companyDbo.getCompany_id())
                .employeeDboList(companyDbo.getEmployeeDboList())
                .name(companyDbo.getName())
                .build();
    }
}

