package com.example.controller;


import com.example.dto.UserDto;
import com.example.entites.User;
import com.example.exception.MyException;
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
    public ResponseEntity<List<UserDto>> register(@Valid @RequestBody UserDto userDto, BindingResult br) {
        if (br.hasErrors()){
            throw new MyException("Məlumatlar boş ola  bilməz !", br);
        }
        userService.createUser(userDto);
        List<UserDto> userDtoList = userService.getAllUsers();
        return ResponseEntity.ok(userDtoList);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        String username = authentication.getName();
        String token = jwtTokenProvider.generateToken(username);
        return ResponseEntity.ok(token);
    }
}
