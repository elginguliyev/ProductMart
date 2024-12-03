package com.example.services.impl;

import com.example.exception.ExistisEmailException;
import com.example.exception.MyException;
import com.example.request.UserRequest;
import com.example.entites.Role;
import com.example.entites.User;
import com.example.repository.UserRepository;
import com.example.response.UserResponse;
import com.example.services.inter.UserService;
import com.example.dto.UserToUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserService {


    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;



    @Override
    public Long register(UserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new ExistisEmailException("User with email " + userRequest.getEmail() + " already exists");
        }
        User user = new User();

        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setCreatedAt(LocalDateTime.now());
         userRepository.save(user);
         return user.getId();
    }

    @Override
    public UserResponse getUser(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new MyException("Istifadəçi tapılmadı", null));

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
    public void update(Principal principal, UserRequest userRequest) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new MyException("Istifadəçi tapılmadı", null));

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
    public void delete(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new MyException("Istifadəçi tapılmadı", null));
        userRepository.delete(user);
    }
}
