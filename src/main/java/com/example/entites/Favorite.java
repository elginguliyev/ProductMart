package com.example.entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "favorite")
public class Favorite {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
