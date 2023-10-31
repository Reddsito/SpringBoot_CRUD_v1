package com.cursojava.curso.services.Implement;

import com.cursojava.curso.Repository.UserRepository;
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
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> findByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<ResponseUserDto> findByName(String name) {
        return userRepository.findByName( name );
    }

    @Override
    @Transactional
    public User updateUser(UserDto userDto, Integer id) {

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
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User deleteUser(Integer id) {

        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            throw new ResourceNotFoundException("client", "id", id);
        }
        userRepository.delete(user);
        return user;
    }

    @Override
    @Transactional
    public boolean existsById(Integer id) {
        return userRepository.existsById(id);
    }
}
