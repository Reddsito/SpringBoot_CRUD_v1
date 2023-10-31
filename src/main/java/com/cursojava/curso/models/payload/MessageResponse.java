package com.cursojava.curso.models.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
public class MessageResponse {
    private String message;
    private Object response;
    private HttpStatus status_code;

    public MessageResponse(String message, Object response, HttpStatus status_code) {
        this.message = message;
        this.response = response;
        this.status_code = status_code;
    }
}