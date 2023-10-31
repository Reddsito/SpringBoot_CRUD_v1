package com.cursojava.curso.models.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ApiResponse {
    private Date date = new Date();
    private String response;
    private String url;

    public ApiResponse(String response, String url) {
        this.response = response;
        this.url = url.replace("uri=", "");
    }
}
