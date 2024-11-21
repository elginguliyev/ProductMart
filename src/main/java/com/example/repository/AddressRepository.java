package com.example.repository;

import com.example.entites.Address;
import com.example.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface
AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUser(User user);

    Optional<Address> findByIdAndUser(Long id, User user);

}
