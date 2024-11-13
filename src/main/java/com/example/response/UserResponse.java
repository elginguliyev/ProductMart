package com.example.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String name;

    private String surname;

    private String username;

    private String email;

    private LocalDateTime createdAt;
}
