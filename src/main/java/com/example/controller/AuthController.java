package com.example.controller;


import com.example.exception.MyException;
import com.example.exception.UserAndPasswordException;
import com.example.request.AdminRequest;
import com.example.request.LoginRequest;
import com.example.request.UserRequest;
import com.example.security.MyTokenManager;
import com.example.services.inter.AdminService;
import com.example.services.inter.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/auth")
@CrossOrigin(value = "*")
@RequiredArgsConstructor
public class AuthController {


    private final UserService userService;

    private final AdminService adminService;

    private final MyTokenManager jwtTokenProvider;

    private final AuthenticationManager authenticationManager;



    @PostMapping
    public ResponseEntity<Long> register(@Valid @RequestBody UserRequest userRequest,
                                                       BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException("Məlumatlar düzgün daxil edilməyib", br);
        }
       Long userId= userService.register(userRequest);
        return ResponseEntity.ok(userId);
    }
    @PostMapping("admin")
    public ResponseEntity<Long> registerAdmin(@Valid @RequestBody AdminRequest adminRequest,
                                         BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException("Məlumatlar düzgün daxil edilməyib", br);
        }
        Long adminId= adminService.register(adminRequest);
        return ResponseEntity.ok(adminId);
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
