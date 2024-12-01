package com.example.services.impl;

import com.example.entites.Role;
import com.example.entites.User;
import com.example.exception.ExistisEmailException;
import com.example.repository.UserRepository;
import com.example.request.AdminRequest;
import com.example.services.inter.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class AdminServicesImpl implements AdminService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper mapper;


    @Override
    public Long register(AdminRequest adminRequest) {
        if (userRepository.existsByEmail(adminRequest.getEmail())) {
            throw new ExistisEmailException("Admin with email " + adminRequest.getEmail() + " already exists");
        }
        User user = new User();
        mapper.map(adminRequest, user);
        user.setPassword(passwordEncoder.encode(adminRequest.getPassword()));
        user.setRole(Role.ROLE_ADMIN);
        userRepository.save(user);
        return user.getId();
    }
}
