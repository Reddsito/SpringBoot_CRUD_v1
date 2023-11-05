package com.cursojava.curso.services;

import com.cursojava.curso.models.dto.ResponseUserDto;
import com.cursojava.curso.models.dto.UserDto;
import com.cursojava.curso.models.entity.User;
import com.cursojava.curso.models.payload.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponse findAllUsers();
    Optional<User> findByEmail(String email);
    UserResponse findUserById(Integer id);
    UserResponse findByName(String name);
    UserResponse updateUser(UserDto userDto, Integer id);
    UserResponse deleteUser(Integer id);
    boolean existsById(Integer id);

}
