package com.cursojava.curso.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    String name;
    String lastname;
    String email;
    Integer phoneNumber;
    String password;

}
