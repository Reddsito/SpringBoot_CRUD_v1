package com.cursojava.curso.services;

import com.cursojava.curso.models.dto.ResponseUserDto;
import com.cursojava.curso.models.dto.UserDto;
import com.cursojava.curso.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> findByEmail(String email);
    User findUserById(Integer id);
    Optional<ResponseUserDto> findByName(String name);
    User updateUser(UserDto userDto, Integer id);
    User deleteUser(Integer id);
    boolean existsById(Integer id);

}
