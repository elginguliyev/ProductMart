package com.example.handlers;

import com.example.exception.ExistisEmailException;
import com.example.exception.NotFoundException;
import com.example.exception.UserRequestException;
import com.example.exception.UserAndPasswordException;
import com.example.response.CustomErrorMesage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.response.ErrorResponse;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class ExceptionHandlers {


    @ExceptionHandler(UserRequestException.class)
    public ErrorResponse myHandlerExc(UserRequestException userRequestException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(userRequestException.getMessage());
        List<CustomErrorMesage> customErrorMesages = userRequestException.getBr().getFieldErrors().stream()
                .map(fieldError -> new CustomErrorMesage(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        errorResponse.setValidations(customErrorMesages);
        return errorResponse;
    }

    @ExceptionHandler(UserAndPasswordException.class)
    public ErrorResponse myUserAndPassword(UserAndPasswordException userAndPasswordException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(userAndPasswordException.getMessage());
        List<CustomErrorMesage> customErrorMesages = userAndPasswordException.getBr().getFieldErrors().stream()
                .map(fieldError -> new CustomErrorMesage(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        errorResponse.setValidations(customErrorMesages);

        return errorResponse;
    }

    @ExceptionHandler(ExistisEmailException.class)
    public ErrorResponse handleExistisEmail(ExistisEmailException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleUserNotFound(NotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        return errorResponse;
    }

}
