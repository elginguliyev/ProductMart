package com.example.controller;


import com.example.exception.UserAndPasswordException;
import com.example.request.LoginRequest;
import com.example.request.UserRequest;
import com.example.response.UserResponse;
import com.example.security.MyTokenManager;
import com.example.services.inter.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/auth")
@CrossOrigin(value = "*")
public class AuthController {


    private final UserService userService;

    private final MyTokenManager jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService,
                          MyTokenManager jwtTokenProvider,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<List<UserResponse>> register(@Valid
                                                       @RequestBody UserRequest userRequest,
                                                       BindingResult br) {
        if (br.hasErrors()) {
            throw new UserRequestException("Məlumatlar düzgün daxil edilməyib", br);
        }
        userService.createUser(userRequest);
        List<UserResponse> responseList = userService.getAllUsers();
        return ResponseEntity.ok(responseList);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid
                                        @RequestBody LoginRequest loginRequest,
                                        BindingResult br) {
        if (br.hasErrors()) {
            throw new UserAndPasswordException("Məlumatlar düzgün daxil edilməyib", br);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword())
        );
        String username = authentication.getName();
        String token = jwtTokenProvider.generateToken(username);
        return ResponseEntity.ok(token);
    }
}
