package com.example.services.inter;

import com.example.entites.Favorite;
import com.example.response.FavoriteResponse;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface FavoriteServices {

    FavoriteResponse addFavorite(Principal principal, Long productId);

    void deleteFavorite(Principal principal, Long favoriteId);

    List<FavoriteResponse> getAllFavorite(Principal principal);
}
