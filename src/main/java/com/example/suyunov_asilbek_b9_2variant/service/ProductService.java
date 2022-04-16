package com.example.suyunov_asilbek_b9_2variant.service;

;
import com.example.suyunov_asilbek_b9_2variant.dto.ApiResponse;
import com.example.suyunov_asilbek_b9_2variant.dto.ProductDto;
import com.example.suyunov_asilbek_b9_2variant.entity.Category;
import com.example.suyunov_asilbek_b9_2variant.entity.Product;
import com.example.suyunov_asilbek_b9_2variant.repository.CategoryRepository;
import com.example.suyunov_asilbek_b9_2variant.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;

    public ApiResponse add(ProductDto dto) {
        if (productRepository.existsByName(dto.getName())) {
            return new ApiResponse("Already exist",false);
        }
        Product product=new Product();
        Optional<Category> byId = categoryRepository.findById(dto.getCategoryId());
        product.setCategory(byId.get());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        productRepository.save(product);
        return new ApiResponse("Added",true);
    }

    public ApiResponse edit(Integer id, ProductDto dto) {
        Optional<Product> byId = productRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Not found",false);
        }
        Product product = byId.get();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        Optional<Category> byId1 = categoryRepository.findById(dto.getCategoryId());
        product.setCategory(byId1.get());
        productRepository.save(product);
        return new ApiResponse("Edited",true);
    }
}
