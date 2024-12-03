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
@RequestMapping(path = "/api/profile")
@CrossOrigin("*")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @Operation(summary = "ID gore istifadeci tapir")
    @GetMapping
    public ResponseEntity<UserResponse> getByIdUser(Principal principal) {
        UserResponse userResponse = userService.getUser(principal);
        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping(path = "/update")
    public ResponseEntity<Void> updateUser(Principal principal,
                                           @RequestBody UserRequest userRequest) {
        userService.update(principal, userRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> deleteByUser(Principal principal) {
        userService.delete(principal);
        return ResponseEntity.ok("Hesabınız uğurla silindi");
    }


    @GetMapping("/info")
    public String getUserInfo(Principal principal) {
        String username = principal.getName(); // Hal-hazırda giriş etmiş istifadəçinin adı
        return "Current User: " + username;
    }
}
