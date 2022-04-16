package com.example.suyunov_asilbek_b9_2variant.controller;


import com.example.suyunov_asilbek_b9_2variant.dto.ApiResponse;
import com.example.suyunov_asilbek_b9_2variant.dto.CategoryDto;
import com.example.suyunov_asilbek_b9_2variant.repository.CategoryRepository;
import com.example.suyunov_asilbek_b9_2variant.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    final CategoryRepository categoryRepository;
    final CategoryService categoryService;

    @GetMapping
    public HttpEntity<?> getAll(){
       return ResponseEntity.ok().body(categoryRepository.findAll());
    }
    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse=categoryService.add(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@Valid @RequestBody CategoryDto dto){
        ApiResponse apiResponse=categoryService.edit(id,dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
       categoryRepository.deleteById(id);
       return ResponseEntity.noContent().build();
    }
}
