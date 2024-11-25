package com.example.controller;

import com.example.request.CommentRequest;
import com.example.services.inter.CommentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/")
public class CommentController {

    private final CommentServices commentServices;

    @PostMapping(path = "product/{producId}/comment_created")
    public ResponseEntity<String> addComment(Principal principal,
                                             @PathVariable Long producId,
                                             @RequestBody CommentRequest commentRequest) {
        commentServices.addComment(principal, producId, commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment add successfully");
    }

    @PutMapping(path = "comment")
    public ResponseEntity<String> updateComment(Principal principal,
                                                @PathVariable Long commentId,
                                                @RequestBody CommentRequest commentRequest) {
        commentServices.updateComment(principal, commentId, commentRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Comment add successfully");
    }

    @DeleteMapping(path = "comment")
    public ResponseEntity<String> deleteComment(Principal principal,
                                                @RequestParam Long commentId) {
        commentServices.deleteComment(principal,  commentId);
        return ResponseEntity.status(HttpStatus.OK).body("Comment add successfully");
    }

}
