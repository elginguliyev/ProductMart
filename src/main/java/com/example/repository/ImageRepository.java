package com.example.repository;

import com.example.entites.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
