package com.example.microservices.mapper;

import com.example.microservices.dbo.CompanyDbo;
import com.example.microservices.mapper.typeHandler.UuidTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface CompanyMapper {
    @Select("Select * from company")
    @Results(value = {
            @Result(id = true, property = "company_id", column = "company_id",
                    jdbcType = JdbcType.OTHER, typeHandler = UuidTypeHandler.class),
            @Result(property = "name", column = "name"),
            @Result(property = "employeeList", column = "company_id", javaType = List.class,
                    many = @Many(select = "selectEmployeeByCompany"))
    })
    List<CompanyDbo> selectAll();

    @Select("Select * from company where company_id = #{company_id}")
    @Results(value = {
            @Result(id = true, property = "company_id", column = "company_id",
                    jdbcType = JdbcType.OTHER, typeHandler = UuidTypeHandler.class),
            @Result(property = "name", column = "name"),
            @Result(property = "employeeList", column = "company_id", javaType = List.class,
                    many = @Many(select = "selectEmployeeByCompany"))
    })
    Optional<CompanyDbo> selectCompanyById(UUID company_id);



}
