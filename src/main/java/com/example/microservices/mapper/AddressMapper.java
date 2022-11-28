package com.example.microservices.mapper;

import com.example.microservices.dbo.Address;
import com.example.microservices.mapper.typeHandler.UuidTypeHandler;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.UUID;

@Mapper
public interface AddressMapper {
    @Select("Select * from company")
    @Results(value = {
            @Result(id = true, property = "university_id", column = "university_id",
                    jdbcType = JdbcType.OTHER, typeHandler = UuidTypeHandler.class),
            @Result(property = "name", column = "name")
    })
    Address getById(UUID address_id);
}
