package com.example.repository;

import com.example.entites.Comment;
import com.example.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndUser(Long commentId, User user);

    Optional<Comment> findByUser(User user);
}
