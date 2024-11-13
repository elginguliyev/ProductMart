package com.example.services.inter;

import com.example.request.UserRequest;
import com.example.entites.User;
import com.example.response.UserResponse;

import java.util.List;

public interface UserService {

    User createUser(UserRequest userRequest);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    void updateUser(Long id, UserRequest userRequest);

    void deleteUser(Long id);
}

