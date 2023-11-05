package com.cursojava.curso.repository;

import com.cursojava.curso.models.dto.ResponseUserDto;
import com.cursojava.curso.models.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .name("Enriqueque")
                .email("Jose@gmail.com")
                .lastname("Jose")
                .phoneNumber(2134123)
                .password("asjdasd")
                .build();

        testEntityManager.persist(user);
    }

    @Test
    public void findUserByNameFound() {
        Optional<ResponseUserDto> user = userRepository.findByName("Enriqueque");
        assertEquals(user.get().getName(), "Enriqueque");
    }

    @Test
    public void findUserByNameNotFound() {
        Optional<ResponseUserDto> user = userRepository.findByName("perro");
        assertEquals(user, Optional.empty());
    }

}