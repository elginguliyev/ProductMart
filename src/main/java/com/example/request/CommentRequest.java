package com.example.request;

import lombok.Data;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.time.LocalDateTime;
@Data
public class CommentRequest {

    private Long id;

    private String content;

    private LocalDateTime createdAt;

    private Long userId;

    private Long productId;
}
