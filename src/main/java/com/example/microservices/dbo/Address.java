package com.example.microservices.dbo;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private UUID address_id;
    private String name;
}
