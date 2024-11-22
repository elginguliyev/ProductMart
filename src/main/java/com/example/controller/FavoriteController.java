package com.example.controller;

import com.example.response.FavoriteResponse;
import com.example.services.inter.FavoriteServices;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/api/")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteServices favoriteServices;




    @PostMapping
    public ResponseEntity<FavoriteResponse> addFavorite(Principal principal,
                                                        @PathVariable Long productId) {
        FavoriteResponse favoriteResponse = favoriteServices.addFavorite(principal, productId);
        return ResponseEntity.status(HttpStatus.OK).body(favoriteResponse);
    }




    @DeleteMapping(path = "favorite/{productId}")
    public ResponseEntity<Void> deleteFavorite(Principal principal,
                                               @PathVariable Long productId) {
        favoriteServices.deleteFavorite(principal, productId);
        return ResponseEntity.noContent().build();
    }



    @GetMapping(path = "favorites")
    public ResponseEntity<List<FavoriteResponse>> getAllFavorite(Principal principal) {
        List<FavoriteResponse> favoriteResponses = favoriteServices.getAllFavorite(principal);

        return ResponseEntity.status(HttpStatus.OK).body(favoriteResponses);
    }

}
