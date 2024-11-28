package com.example.controller;

import com.example.request.UserRequest;
import com.example.response.UserResponse;
import com.example.services.inter.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Butun istidafecileri getirir")
    @GetMapping("users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> responseList = userService.getAllUsers();
        return ResponseEntity.ok(responseList);
    }
    @Operation(summary = "ID gore istifadeci tapir")
    @GetMapping("user{id}")
    public ResponseEntity<UserResponse> getByIdUser(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping(path = "user/{id}")
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


    @GetMapping("user-info")
    public String getUserInfo(Principal principal) {
        String username = principal.getName(); // Hal-hazırda giriş etmiş istifadəçinin adı
        return "Current User: " + username;
    }
}
