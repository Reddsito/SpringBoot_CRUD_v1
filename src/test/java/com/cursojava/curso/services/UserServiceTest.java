package com.cursojava.curso.services;

import com.cursojava.curso.models.payload.UserResponse;
import com.cursojava.curso.repository.UserRepository;
import com.cursojava.curso.models.dto.ResponseUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private  UserService userService;
    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        ResponseUserDto user = ResponseUserDto.builder()
                .name("Enrique")
                .lastname("Jesus")
                .email("pedro@gmail.com")
                .phoneNumber(123123)
                .build();

        Mockito.when(userRepository.findByName("Enrique")).thenReturn(Optional.of(user));
    }

    @Test
    @DisplayName("Prueba de obtención de información de un user enviando un nombre válido")
    public void findByNameShouldFound() {
        String userName = "Enrique";
        UserResponse user = userService.findByName(userName);
        assertEquals(userName, user.getResponse());
        System.out.println("user = " + user);

    }

}