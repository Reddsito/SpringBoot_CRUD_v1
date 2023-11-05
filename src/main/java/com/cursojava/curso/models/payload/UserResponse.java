package com.cursojava.curso.models.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@Builder
public class UserResponse {
    private String message;
    private Object response;
    private Object errors;
    private Integer status_code;

    public UserResponse(String message, Object response, Object errors, Integer status_code) {
        this.message = message;
        this.response = response;
        this.errors = errors;
        this.status_code = status_code;
    }
}