package com.cursojava.curso.services;

import com.cursojava.curso.models.dto.LoginRequestDto;
import com.cursojava.curso.models.dto.RegisterRequestDto;
import com.cursojava.curso.models.payload.AuthResponse;

public interface AuthService {
    public AuthResponse login(LoginRequestDto request);
    public AuthResponse register(RegisterRequestDto request);


}
