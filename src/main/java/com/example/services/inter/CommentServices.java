package com.example.services.inter;

import com.example.request.CommentRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public interface CommentServices {

    void addComment(Principal principal, CommentRequest commentRequest);

    void updateComment(Principal principal, Long commentId, CommentRequest commentRequest);

    void deleteComment(Principal principal, Long commentId);
}
