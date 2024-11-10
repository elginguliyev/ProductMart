package com.example.dto;

import com.example.entites.Role;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String name;

    private String surname;

    private String username;

    private String email;

    private String password;

    private String role;

}
