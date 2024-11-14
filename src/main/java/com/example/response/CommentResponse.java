package com.example.response;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentResponse {

    private String content;

    private LocalDateTime createdAt;

    private Long userId;

}
