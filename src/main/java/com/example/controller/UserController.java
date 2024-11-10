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
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> usersDtoList = userService.getAllUsers();
        return ResponseEntity.ok(usersDtoList);
    }

    @GetMapping("user{id}")
    public ResponseEntity<UserDto> getByIdUser(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping(path = "user/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,
                                              @RequestBody UserDto userDto) {
        userService.updateUser(id, userDto);
        UserDto userDto2 = getByIdUser(id).getBody();
        return ResponseEntity.ok(userDto2);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<String> deleteByUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User with ID " + id + " deleted successfully");
    }
}
