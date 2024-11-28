package com.example.services.impl;

import com.example.entites.Comment;
import com.example.entites.Product;
import com.example.entites.User;
import com.example.exception.MyException;
import com.example.repository.CommentRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import com.example.request.CommentRequest;
import com.example.services.inter.CommentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CommentServicesImpl implements CommentServices {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Override
    public void addComment(Principal principal, Long productId, CommentRequest commentRequest) {

        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new MyException("İstifadəçi tapılmadı", null));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new MyException("Məhsul tapılmadı", null));

        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setUser(user);
        comment.setProduct(product);
        commentRepository.save(comment);
    }

    @Override
    public void updateComment(Principal principal,   Long commentId, CommentRequest commentRequest) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new MyException("İstifadəçi tapılmadı",null));


        Comment comment = commentRepository.findByIdAndUser(commentId, user)
                .orElseThrow(() -> new MyException("Şərh  tapılmadı",null));

        comment.setContent(commentRequest.getContent());
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Principal principal,  Long commentId) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new MyException("İstifadəçi tapılmadı",null));

        Comment comment = commentRepository.findByIdAndUser(commentId, user)
                .orElseThrow(() -> new MyException("Şərh  tapılmadı",null));


        commentRepository.delete(comment);
    }
}
