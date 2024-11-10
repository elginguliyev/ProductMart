package com.example.controller;

import com.example.dto.UserDto;
import com.example.entites.User;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserDto> getByIdUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        return ResponseEntity.ok(userDto);
    }

    @PutMapping(path ="user/{id}" )
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,
                                              @RequestBody UserDto userDto) {
        UserDto userDto1 = getByIdUser(id).getBody();
        userDto1.setName(userDto.getName());
        userDto1.setSurname(userDto.getSurname());
        userDto1.setEmail(userDto.getEmail());
        userDto1.setUsername(userDto.getUsername());
        return null;
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<String> deleteByUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User with ID " + id + " deleted successfully");
    }
}
