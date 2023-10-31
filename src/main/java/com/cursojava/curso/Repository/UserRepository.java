package com.cursojava.curso.Repository;

import com.cursojava.curso.models.dto.ResponseUserDto;
import com.cursojava.curso.models.dto.UserDto;
import com.cursojava.curso.models.entity.User;
import org.apache.coyote.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<ResponseUserDto> findByName(String name);


}
