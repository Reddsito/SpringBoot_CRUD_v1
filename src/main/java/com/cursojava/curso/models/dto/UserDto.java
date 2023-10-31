package com.cursojava.curso.models.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private Integer id;

    @NotNull
    @NotEmpty(message = "name is required")
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Size(min = 3, max = 50)
    @NotEmpty(message = "lastname is required")
    private String lastname;

    @NotNull
    @Size(min = 3, max = 50)
    @Email
    @NotEmpty(message = "email is required")
    private String email;

    @NotNull
    @NotEmpty(message = "phone number is required")
    @Digits(integer = 15, fraction = 0)
    private Integer phoneNumber;

    @NotNull
    @NotEmpty(message = "password is required")
    private String password;



}
