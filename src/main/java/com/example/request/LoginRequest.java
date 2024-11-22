package com.example.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Username boş ola bilməz")
    @Size(min = 3, message = "Username ən azı 3 simvol olmalıdır")
    private String username;
    @NotBlank(message = "Parol boş ola  bilməz")
    @Size(min = 6, message = "Password ən azı 6 simvol olmalıdır")
    private String password;
}
