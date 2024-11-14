package com.example.services.inter;

import com.example.request.CommentRequest;
import org.springframework.stereotype.Service;

@Service
public interface CommentServices {

    void addComment(CommentRequest commentRequest);

    void updateComment(CommentRequest commentRequest);

    void deleteComment(Long commentId);
}
