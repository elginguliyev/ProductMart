package com.example.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_surname")
    private String surname;


    @Column(name = "user_ame")
    private String username;


    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> order;


}
