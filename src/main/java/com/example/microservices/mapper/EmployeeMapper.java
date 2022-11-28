package com.example.microservices.mapper;

import com.example.microservices.dbo.CompanyDbo;
import com.example.microservices.dbo.EmployeeDbo;
import com.example.microservices.mapper.typeHandler.UuidTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface EmployeeMapper {
    @Select("Select * from employee where company_id = #{company_id}")
    @Results(value = {
            @Result(id = true, property = "company_id", column = "company_id",
                    jdbcType = JdbcType.OTHER, typeHandler = UuidTypeHandler.class),
            @Result(property = "name", column = "name"),
            @Result(property = "employeeList", column = "company_id", javaType = List.class,
                    one = @One(select = "com.example.microservices.mapper.AddressMapper.getById")),
            @Result(property = "status", column = "status")
    })
    List<CompanyDbo> selectEmployeeByCompany(UUID company_id);

    @Select("Select * from employee")
    @Results(value = {
            @Result(id = true, property = "company_id", column = "company_id",
                    jdbcType = JdbcType.OTHER, typeHandler = UuidTypeHandler.class),
            @Result(property = "name", column = "name"),
            @Result(property = "employeeList", column = "company_id", javaType = List.class,
                    one = @One(select = "com.example.microservices.mapper.AddressMapper.getById")),
            @Result(property = "status", column = "status"),
            @Result(property = "company_id", column = "company_id",
                    one = @One(select = "com.example.microservices.mapper.CompanyMapper.selectCompanyById"))
    })
    List<EmployeeDbo> selectAllEmployees();

    @Select("Select * from employee where company_id = #{company_id}")
    @Results(value = {
            @Result(id = true, property = "company_id", column = "company_id",
                    jdbcType = JdbcType.OTHER, typeHandler = UuidTypeHandler.class),
            @Result(property = "name", column = "name"),
            @Result(property = "employeeList", column = "company_id", javaType = List.class,
                    one = @One(select = "com.example.microservices.mapper.AddressMapper.getById")),
            @Result(property = "status", column = "status"),
            @Result(property = "company_id", column = "company_id",
                    one = @One(select = "com.example.microservices.mapper.CompanyMapper.selectCompanyById"))
    })
    Optional<EmployeeDbo> selectEmployeeById(UUID company_id);
}
