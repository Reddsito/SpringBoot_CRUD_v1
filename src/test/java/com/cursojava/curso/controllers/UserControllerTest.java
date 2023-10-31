package com.cursojava.curso.controllers;

import com.cursojava.curso.models.dto.UserDto;
import com.cursojava.curso.models.entity.User;
import com.cursojava.curso.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(100)
                .name("Enrique")
                .lastname("Jesus")
                .email("Enrique Querini")
                .phoneNumber(123123)
                .password("asdasd")
                .build();

    }

    public void saveUser() throws Exception {
        UserDto postUser = UserDto.builder()
                .id(100)
                .name("Enrique")
                .lastname("Jesus")
                .email("Jesus@gmail.com")
                .phoneNumber(123123)
                .password("asdasd")
                .build();


        mockMvc.perform(post("/api/v1/user").contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"id\":\"100\", \n" +
                                "    \"name\":\"Enrique\", \n" +
                                "   \"lastname\":\"Jesus\", \n " +
                                "   \"email\":\"Jesus@gmail.com\", \n   " +
                                "   \"phoneNumber\": 123123, \n   " +
                                "    \"password\":\"asdasd\" \n  " +
                                "}"))
                .andExpect(status().isCreated());
    }
}