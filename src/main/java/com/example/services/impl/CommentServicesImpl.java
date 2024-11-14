package com.example.services.impl;

import com.example.entites.Comment;
import com.example.entites.Product;
import com.example.entites.User;
import com.example.repository.CommentRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import com.example.request.CommentRequest;
import com.example.services.inter.CommentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServicesImpl implements CommentServices {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Override
    public void addComment(CommentRequest commentRequest) {

        User user = userRepository.findById(commentRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(commentRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setUser(user);
        comment.setProduct(product);
        commentRepository.save(comment);
    }

    @Override
    public void updateComment(CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(commentRequest.getId())
                .orElseThrow(() -> new RuntimeException("Commnet not found"));

        comment.setContent(commentRequest.getContent());
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
