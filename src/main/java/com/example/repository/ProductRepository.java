package com.example.repository;

import com.example.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingAndLocationAndCategory_Name(String name, String location, String categoryName);

    List<Product> findByNameContainingAndLocation(String name, String location);

    List<Product> findByNameContainingAndCategory_Name(String name, String categoryName);

    List<Product> findByNameContaining(String name);
}

