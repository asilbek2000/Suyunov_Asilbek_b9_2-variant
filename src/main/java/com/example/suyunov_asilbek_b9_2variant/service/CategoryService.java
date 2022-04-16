package com.example.suyunov_asilbek_b9_2variant.service;

import com.example.suyunov_asilbek_b9_2variant.dto.ApiResponse;
import com.example.suyunov_asilbek_b9_2variant.dto.CategoryDto;
import com.example.suyunov_asilbek_b9_2variant.entity.Category;
import com.example.suyunov_asilbek_b9_2variant.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    final CategoryRepository categoryRepository;

    public ApiResponse add(CategoryDto dto) {
        if (categoryRepository.existsByName(dto.getName())) {
            return new ApiResponse("Already exist",false);
        }
        Category category=new Category();
        category.setName(dto.getName());
        category.setActive(dto.isActive());
        categoryRepository.save(category);
        return new ApiResponse("Added",true);
    }

    public ApiResponse edit(Integer id, CategoryDto dto) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Not found",false);
        }
        Category category = byId.get();
        category.setName(dto.getName());
        category.setActive(dto.isActive());
        categoryRepository.save(category);
        return new ApiResponse("Edited",true);
    }


}
