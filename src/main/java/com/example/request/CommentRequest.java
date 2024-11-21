package com.example.request;

import lombok.Data;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.time.LocalDateTime;
@Data
public class CommentRequest {


    private String content;

    private Long productId;
}
