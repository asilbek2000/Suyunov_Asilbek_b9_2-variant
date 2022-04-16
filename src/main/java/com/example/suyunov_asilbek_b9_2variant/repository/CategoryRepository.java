package com.example.suyunov_asilbek_b9_2variant.repository;


import com.example.suyunov_asilbek_b9_2variant.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    boolean existsByName(String name);
}
