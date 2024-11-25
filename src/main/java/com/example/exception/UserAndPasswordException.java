package com.example.exception;

import lombok.Data;
import org.springframework.validation.BindingResult;

@Data
public class UserAndPasswordException extends RuntimeException {

    private BindingResult br;

    public UserAndPasswordException(String message, BindingResult br) {
        super(message);
        this.br = br;
    }

}
