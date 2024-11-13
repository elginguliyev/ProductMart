package com.example.handlers;

import com.example.exception.MyException;
import com.example.response.CustomErrorMesage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.response.ErrorResponse;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class ExceptionHandlers {



    @ExceptionHandler
    public ErrorResponse myHandlerExc(MyException myException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(myException.getMessage());
        List<CustomErrorMesage> customErrorMesages = myException.getBr().getFieldErrors().stream()
                .map(fieldError -> new CustomErrorMesage(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        errorResponse.setValidations(customErrorMesages);
        return errorResponse;
    }


}
