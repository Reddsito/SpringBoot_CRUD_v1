package com.cursojava.curso.controllers;

import com.cursojava.curso.models.dto.LoginRequestDto;
import com.cursojava.curso.models.dto.RegisterRequestDto;
import com.cursojava.curso.models.payload.AuthResponse;
import com.cursojava.curso.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> Login(@RequestBody LoginRequestDto request)
    {
        AuthResponse response = authService.login(request);
        return new ResponseEntity<AuthResponse>(response, HttpStatus.OK);
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequestDto request){
       AuthResponse response = authService.register(request);
       return new ResponseEntity<AuthResponse>(response, HttpStatus.OK);
    }


}
