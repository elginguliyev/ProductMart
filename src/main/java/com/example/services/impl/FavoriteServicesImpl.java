package com.example.services.impl;

import com.example.entites.Favorite;
import com.example.repository.FavoriteRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
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
    public List<Favorite> addFavorite(Principal principal, Long productId) {
        return null;
    }

    @Override
    public void deleteFavorite(Principal principal, Long productId) {

    }
}
