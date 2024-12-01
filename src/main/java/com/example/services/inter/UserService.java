package com.example.services.inter;

import com.example.request.UserRequest;
import com.example.entites.User;
import com.example.response.UserResponse;

import java.security.Principal;
import java.util.List;

public interface UserService {

    Long register(UserRequest userRequest);

    UserResponse getUser(Principal principal);

    List<UserResponse> getAllUsers();

    void update(Principal principal, UserRequest userRequest);

    void delete(Principal principal);
}

