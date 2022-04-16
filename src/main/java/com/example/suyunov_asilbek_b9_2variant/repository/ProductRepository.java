package com.example.suyunov_asilbek_b9_2variant.repository;

import com.example.suyunov_asilbek_b9_2variant.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    boolean existsByName(String name);
}
