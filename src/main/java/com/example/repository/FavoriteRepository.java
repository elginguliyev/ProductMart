package com.example.repository;

import com.example.entites.Favorite;
import com.example.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {


    Favorite findByIdAndUser(Long favoriyeId, User user);

    List<Favorite> findAllByUser(User user);
}
