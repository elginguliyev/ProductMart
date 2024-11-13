package com.example.request;

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
public class UserRequest {

    @NotBlank(message = "Zəhmət olmasa  adınızı daxil edin ")
    @Size(min = 3, max = 15, message = "Username 3 ilə 15 simvol arasında olmalıdır")
    private String name;

    @NotBlank(message = "Zəhmət olmasa  soyadınızı daxil edin ")
    @Size(min = 3, max = 15, message = "Username 3 ilə 15 simvol arasında olmalıdır")
    private String surname;

    @NotBlank(message = "Zəhmət olmasa  istifadəçi adınızı daxil edin ")
    @Size(min = 3, max = 15, message = "Username 3 ilə 15 simvol arasında olmalıdır")
    private String username;

    @NotBlank(message = "Zəhmət olmasa  email ünvaninizi daxil edin ")
    @Email
    private String email;

    @NotBlank(message = "Zəhmət olmasa  parolunuzu daxil edin ")
    private String password;

    private LocalDateTime createdAt;
}
