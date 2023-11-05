package com.cursojava.curso.services.Implement;


import com.cursojava.curso.models.payload.UserResponse;
import com.cursojava.curso.repository.UserRepository;
import com.cursojava.curso.exceptions.ResourceNotFoundException;
import com.cursojava.curso.models.dto.ResponseUserDto;
import com.cursojava.curso.models.dto.UserDto;
import com.cursojava.curso.models.entity.User;
import com.cursojava.curso.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserImplService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserResponse findAllUsers() {
        List<User> users = userRepository.findAll();

        if(users.isEmpty()) {
            throw new ResourceNotFoundException("users");
        } else {
            return UserResponse.builder()
                    .response(users)
                    .status_code(200)
                    .build();
        }
    }

    @Override
    public Optional<User> findByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("user", "id", id));

        return UserResponse.builder()
                    .response(user)
                    .status_code(200)
                    .build();
    }

    @Override
    public UserResponse findByName(String name) {
        ResponseUserDto user = userRepository.findByName(name).orElseThrow( () -> new ResourceNotFoundException("user", "name", name));

        return UserResponse.builder()
                .response(user)
                .status_code(200)
                .build();
    }

    @Override
    @Transactional
    public UserResponse updateUser(UserDto userDto, Integer id) {

        if ( !existsById(id) ) {
            throw new ResourceNotFoundException("client", "id", id);
        }

        userDto.setId(id);
        User user = User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .password(userDto.getPassword())
                .build();
        User userResponse = userRepository.save(user);
        return UserResponse.builder()
                .message("user updated")
                .response(userResponse)
                .status_code(200)
                .build();
    }

    @Override
    @Transactional
    public UserResponse deleteUser(Integer id) {

        User user = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("user", "id", id) );
        userRepository.delete(user);
        return UserResponse.builder()
                .message("user deleted")
                .response(user)
                .status_code(204)
                .build();
    }

    @Override
    @Transactional
    public boolean existsById(Integer id) {
        return userRepository.existsById(id);
    }
}
