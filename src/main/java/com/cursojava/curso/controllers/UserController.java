package com.cursojava.curso.controllers;

import com.cursojava.curso.exceptions.ResourceNotFoundException;
import com.cursojava.curso.models.dto.ResponseUserDto;
import com.cursojava.curso.models.dto.UserDto;
import com.cursojava.curso.models.entity.User;
import com.cursojava.curso.models.payload.MessageResponse;
import com.cursojava.curso.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class UserController{

    @Autowired
    private UserService userService;

    @GetMapping(value = "users")
    public ResponseEntity<MessageResponse> getUsers() {
        List<User> listUser =  userService.findAllUsers();
        if( listUser == null || listUser.isEmpty()) {
            throw new ResourceNotFoundException("clients");
        } else {
            return new ResponseEntity<MessageResponse>(MessageResponse.builder()
                    .response(listUser)
                    .status_code(HttpStatus.OK)
                    .build(),
                    HttpStatus.OK);

        }
    }

    @GetMapping(value = "userById/{id}")
    public ResponseEntity<MessageResponse> getUserById(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        if( user == null ) {
            throw new ResourceNotFoundException("client", "id", id);
        } else {
            return new ResponseEntity<MessageResponse>(MessageResponse.builder()
                    .response(user)
                    .status_code(HttpStatus.OK)
                    .build(),
                    HttpStatus.OK);
        }
    }

    @GetMapping(value = "userByName/{name}")
    public ResponseEntity<MessageResponse> findUserByName(@PathVariable String name) {
        ResponseUserDto user = userService.findByName(name).orElse(null);
        if( user == null ) {
            throw new ResourceNotFoundException("client", "name", name);
        } else {
            return new ResponseEntity<MessageResponse>(MessageResponse.builder()
                    .response(user)
                    .message("User found")
                    .status_code(HttpStatus.OK)
                    .build(),
                    HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "user/{id}")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable Integer id) {

       User user = userService.deleteUser(id);
        return new ResponseEntity<MessageResponse>(MessageResponse.builder()
                .response(user)
                .message("User deleted")
                .status_code(HttpStatus.NO_CONTENT)
                .build(),
                HttpStatus.NO_CONTENT);

    }


    @PutMapping(value = "user/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageResponse> updateUser(@RequestBody @Valid UserDto userDto, @RequestParam Integer id) {

        User user = userService.updateUser(userDto, id);
        return new ResponseEntity<MessageResponse>(MessageResponse.builder()
                .response(user)
                .message("User updated")
                .status_code(HttpStatus.CREATED)
                .build(),
                HttpStatus.CREATED);
    }

}
