package com.example.entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;
    @Column(name = "image_url")
    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
