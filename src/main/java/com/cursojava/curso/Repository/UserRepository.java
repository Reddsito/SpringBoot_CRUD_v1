package com.cursojava.curso.repository;

import com.cursojava.curso.models.dto.ResponseUserDto;
import com.cursojava.curso.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<ResponseUserDto> findByName(String name);


}
