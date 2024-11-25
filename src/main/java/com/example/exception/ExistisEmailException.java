package com.example.exception;

import lombok.Data;
import org.springframework.validation.BindingResult;
@Data
public class ExistisEmailException extends RuntimeException {

    public ExistisEmailException(String mesagge){
        super(mesagge);
    }

}
