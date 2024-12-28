package com.stock.stock_management.application.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientResponse {

    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private Integer quantity;
}
