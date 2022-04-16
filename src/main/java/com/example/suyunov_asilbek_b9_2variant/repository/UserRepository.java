package com.example.suyunov_asilbek_b9_2variant.repository;

import com.example.suyunov_asilbek_b9_2variant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String name);

    User getByUserName(String username);
}
