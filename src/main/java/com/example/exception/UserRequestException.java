package com.example.exception;

import lombok.Data;
import org.springframework.validation.BindingResult;

@Data
public class UserRequestException extends RuntimeException {

    private BindingResult br;


    public UserRequestException(String message, BindingResult br) {
        super(message);
        this.br = br;
    }
}
