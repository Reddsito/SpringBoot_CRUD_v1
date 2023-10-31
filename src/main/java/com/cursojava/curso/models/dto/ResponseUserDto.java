package com.cursojava.curso.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseUserDto {

    private String name;
    private String lastname;
    private String email;
    private Integer phoneNumber;
}
