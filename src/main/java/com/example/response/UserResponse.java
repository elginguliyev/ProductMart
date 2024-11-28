package com.example.response;

import io.swagger.v3.oas.annotations.media.Schema;

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
    @Schema(type = "string", example = "2024-11-28T14:30")
    private LocalDateTime createdAt;
}
