package com.example.exception;

import lombok.Data;
import org.springframework.validation.BindingResult;

@Data
public class MyException extends RuntimeException {

    private BindingResult br;


    public MyException(String message, BindingResult br) {
        super(message);
        this.br = br;
    }
}
