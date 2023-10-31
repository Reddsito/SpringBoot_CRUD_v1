package com.cursojava.curso.services.Implement;

import com.cursojava.curso.Repository.UserRepository;
import com.cursojava.curso.configuration.jwt.JwtService;
import com.cursojava.curso.configuration.jwt.JwtServiceImpl;
import com.cursojava.curso.models.dto.LoginRequestDto;
import com.cursojava.curso.models.dto.RegisterRequestDto;
import com.cursojava.curso.models.entity.User;
import com.cursojava.curso.models.enums.Role;
import com.cursojava.curso.models.payload.AuthResponse;
import com.cursojava.curso.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class AuthImplService implements AuthService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(AuthImplService.class);


    @Override
    @Transactional
    public AuthResponse login(LoginRequestDto request) {
        logger.info("Iniciando autenticación...");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (Exception e ) {
            logger.error("Error durante la autenticación: {}", e.getMessage());
            throw e; // O manejar el error según lo necesites
        }

        logger.info("Autenticación exitosa");

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        String jwt = jwtService.generateToken(user);

        logger.info("Token generado con éxito");

        return AuthResponse.builder().token(jwt).build();
    }

    @Override
    @Transactional
    public AuthResponse register(RegisterRequestDto request) {

        User userDuplicated = userRepository.findByEmail( request.getEmail() ).orElse(null);

        if( userDuplicated != null ) {
            throw new DuplicateKeyException("Email duplicated");
        }

        User user = User.builder()
                .name(request.getName())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password( passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwt)
                .build();
    }
}
