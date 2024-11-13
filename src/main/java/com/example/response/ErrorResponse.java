package com.example.response;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    private String message;
    private List<CustomErrorMesage> validations;

}
