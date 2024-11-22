package com.example.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.time.LocalDateTime;

@Data
public class CommentRequest {

    @NotBlank(message = "Zəhmət olamsa  bir şərh yazın")
    @Size(min = 0, max = 100)
    private String content;


}
