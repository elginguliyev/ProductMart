package com.example.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressRequest {

    @NotBlank(message = "Zəhmət olmasa şəhər/rayon daxil edin")
    private String city;
    @NotBlank(message = "Zəhmət olmasa ünvan daxil edin")
    private String street;
    @NotBlank(message = "Zəhmət olmasa telfon nömrəsini  daxil edin")
    private String phoneNumber;
}
