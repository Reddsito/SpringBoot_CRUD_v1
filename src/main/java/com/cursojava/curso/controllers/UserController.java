package com.cursojava.curso.controllers;

import com.cursojava.curso.exceptions.ResourceNotFoundException;
import com.cursojava.curso.models.dto.ResponseUserDto;
import com.cursojava.curso.models.dto.UserDto;
import com.cursojava.curso.models.entity.User;
import com.cursojava.curso.models.payload.UserResponse;
import com.cursojava.curso.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class UserController{

    private UserService userService;

    @GetMapping(value = "users")
    public ResponseEntity<UserResponse> getUsers() {
        UserResponse response =  userService.findAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping(value = "userById/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
        UserResponse response = userService.findUserById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "userByName/{name}")
    public ResponseEntity<UserResponse> findUserByName(@PathVariable String name) {
        UserResponse response = userService.findByName(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping(value = "user/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Integer id) {
       UserResponse response = userService.deleteUser(id);
       return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }


    @PutMapping(value = "user/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> updateUser(@RequestBody @Valid UserDto userDto, @RequestParam Integer id) {

        UserResponse response = userService.updateUser(userDto, id);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
