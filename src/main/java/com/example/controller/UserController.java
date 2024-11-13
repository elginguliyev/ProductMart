package com.example.controller;

import com.example.request.UserRequest;
import com.example.response.UserResponse;
import com.example.services.inter.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> responseList = userService.getAllUsers();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("user{id}")
    public ResponseEntity<UserResponse> getByIdUser(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping(path = "user/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                  @RequestBody UserRequest userRequest) {
        userService.updateUser(id, userRequest);
        UserResponse userResponse = getByIdUser(id).getBody();
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<String> deleteByUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User with ID " + id + " deleted successfully");
    }
}
