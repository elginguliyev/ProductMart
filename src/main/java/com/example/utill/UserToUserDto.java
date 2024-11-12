package com.example.utill;

import com.example.dto.UserDto;
import com.example.entites.User;

public class UserToUserDto {

    public static UserDto convertUserDto(User user){
        UserDto userDto=new UserDto();
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().name());
        return userDto;
    }
}
