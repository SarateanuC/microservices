package com.example.microservices;

import com.example.microservices.mapper.CompanyMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MicroservicesApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MicroservicesApplication.class, args);

    }

}
