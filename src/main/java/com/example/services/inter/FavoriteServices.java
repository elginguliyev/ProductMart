package com.example.services.inter;

import com.example.entites.Favorite;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface FavoriteServices {

    List<Favorite> addFavorite(Principal principal, Long productId);

    void deleteFavorite(Principal principal, Long productId);
}
