package com.stock.stock_management.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientRequest {

    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String brand;

}
