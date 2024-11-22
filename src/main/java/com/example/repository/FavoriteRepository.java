package com.example.repository;

import com.example.entites.Favorite;
import com.example.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {


    Favorite findByUser(User user);


}
