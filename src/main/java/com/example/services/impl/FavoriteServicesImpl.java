package com.example.services.impl;

import com.example.dto.FavoriteToFavoriteResp;
import com.example.entites.Favorite;
import com.example.entites.Product;
import com.example.entites.User;
import com.example.repository.FavoriteRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import com.example.response.FavoriteResponse;
import com.example.services.inter.FavoriteServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FavoriteServicesImpl implements FavoriteServices {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final FavoriteRepository favoriteRepository;


    @Override
    public FavoriteResponse addFavorite(Principal principal, Long productId) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not  found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product  not  found"));
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);

        Favorite addFavorite = favoriteRepository.save(favorite);

        return FavoriteToFavoriteResp.toResponse(addFavorite);
    }

    @Override
    public void deleteFavorite(Principal principal, Long favoriteId, Long productId) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not  found"));

        Favorite favorite = favoriteRepository.findByUser(user);
        if (!favorite.getId().equals(favoriteId)){
            throw new RuntimeException("");
        }
         favoriteRepository.delete(favorite);
    }
}
