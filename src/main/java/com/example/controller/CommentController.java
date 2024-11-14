package com.example.controller;

import com.example.request.CommentRequest;
import com.example.services.inter.CommentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path ="/api/v1/")
public class CommentController {

    private final CommentServices commentServices;
    @PostMapping(path ="comment_created" )
    public ResponseEntity<String> addComment(@RequestBody CommentRequest commentRequest){
        commentServices.addComment(commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment add successfully");
    }
    @PutMapping(path ="comment_update" )
    public ResponseEntity<String> updateComment(@RequestBody CommentRequest commentRequest){
        commentServices.updateComment(commentRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Comment add successfully");
    }

    @DeleteMapping(path ="comment_delete" )
    public ResponseEntity<String> deleteComment(@RequestParam Long commentId){
        commentServices.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).body("Comment add successfully");
    }

}
