package com.example.suyunov_asilbek_b9_2variant.controller;


import com.example.suyunov_asilbek_b9_2variant.dto.ApiResponse;
import com.example.suyunov_asilbek_b9_2variant.dto.ProductDto;
import com.example.suyunov_asilbek_b9_2variant.repository.ProductRepository;
import com.example.suyunov_asilbek_b9_2variant.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    final ProductRepository productRepository;
    final ProductService productService;

    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok().body(productRepository.findAll());
    }
    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody ProductDto dto){
        ApiResponse apiResponse=productService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@Valid @RequestBody ProductDto dto){
        ApiResponse apiResponse=productService.edit(id,dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        return ResponseEntity.ok().body(productRepository.findById(id).get());
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProductById(@PathVariable Integer id){
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
