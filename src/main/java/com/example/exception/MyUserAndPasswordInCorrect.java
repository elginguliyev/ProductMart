package com.example.exception;

import lombok.Data;
import org.springframework.validation.BindingResult;

@Data
public class MyUserAndPasswordInCorrect extends RuntimeException {

    private BindingResult br;

    public MyUserAndPasswordInCorrect(String message, BindingResult br) {
        super(message);
        this.br = br;
    }

}
