package com.example.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentResponse {

    private Long id;

    private String content;

    private LocalDateTime createdAt;

    private Long userId;

}
