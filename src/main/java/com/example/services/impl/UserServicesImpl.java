package com.example.services.impl;

import com.example.request.UserRequest;
import com.example.entites.Role;
import com.example.entites.User;
import com.example.repository.UserRepository;
import com.example.response.UserResponse;
import com.example.services.inter.UserService;
import com.example.dto.UserToUserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl implements UserService {


    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    public UserServicesImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException("User with email " + userRequest.getEmail() + " already exists");
        }
        User user = new User();

        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse userResponse = UserToUserResponse.convertUserDto(user);
        return userResponse;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        List<UserResponse> responseList = usersList.stream()
                .map(user -> UserToUserResponse.convertUserDto(user))
                .collect(Collectors.toList());

        return responseList;
    }

    @Override
    public void updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not Found"));
        if (userRequest.getUsername() != null) {
            user.setUsername(userRequest.getUsername());
        }
        if (userRequest.getName() != null) {
            user.setName(userRequest.getName());
        }
        if (userRequest.getSurname() != null) {
            user.setSurname(userRequest.getSurname());
        }
        if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }
        userRepository.save(user);

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
