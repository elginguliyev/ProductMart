package com.example.repository;

import com.example.entites.Cart;
import com.example.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    public Cart findByUser(User user);

}
