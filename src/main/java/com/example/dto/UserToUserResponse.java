package com.example.dto;

import com.example.entites.User;
import com.example.response.UserResponse;

public class UserToUserResponse {

    public static UserResponse convertUserDto(User user){
        UserResponse userResponse =new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setSurname(user.getSurname());
        userResponse.setEmail(user.getEmail());
        userResponse.setUsername(user.getUsername());
        userResponse.setCreatedAt(user.getCreatedAt());
        return userResponse;
    }
}
