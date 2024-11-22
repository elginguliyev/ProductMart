package com.example.dto;

import com.example.entites.Favorite;
import com.example.response.FavoriteResponse;
import com.example.response.ProductResponse;

public class FavoriteToFavoriteResp {


    public static FavoriteResponse toResponse(Favorite favorite) {
        FavoriteResponse favoriteResponse = new FavoriteResponse();

        ProductResponse productResponse = ProductToProductResponse.convertToProduct(favorite.getProduct());

        favoriteResponse.setId(favorite.getId());
        favoriteResponse.setProduct(productResponse);

        return favoriteResponse;
    }
}
