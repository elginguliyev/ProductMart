package com.example.handlers;

import com.example.exception.MyException;
import com.example.exception.MyUserAndPasswordInCorrect;
import com.example.response.CustomErrorMesage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.response.ErrorResponse;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class ExceptionHandlers {


    @ExceptionHandler(MyException.class)
    public ErrorResponse myHandlerExc(MyException myException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(myException.getMessage());
        List<CustomErrorMesage> customErrorMesages = myException.getBr().getFieldErrors().stream()
                .map(fieldError -> new CustomErrorMesage(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        errorResponse.setValidations(customErrorMesages);
        return errorResponse;
    }

    @ExceptionHandler(MyUserAndPasswordInCorrect.class)
    public ErrorResponse myUserAndPassword(MyUserAndPasswordInCorrect myUserAndPasswordInCorrect) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(myUserAndPasswordInCorrect.getMessage());
        List<CustomErrorMesage> customErrorMesages = myUserAndPasswordInCorrect.getBr().getFieldErrors().stream()
                .map(fieldError -> new CustomErrorMesage(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        errorResponse.setValidations(customErrorMesages);

        return errorResponse;
    }


}
