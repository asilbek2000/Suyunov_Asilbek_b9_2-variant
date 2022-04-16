package com.example.suyunov_asilbek_b9_2variant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private String name;
    private Double price;
    private Double amount;
    private Integer categoryId;
}
