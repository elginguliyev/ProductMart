package com.example.services;

import com.example.dto.UserDto;
import com.example.entites.User;

import java.util.List;

public interface UserService {

    User createUser(UserDto userDto);

    User getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}

