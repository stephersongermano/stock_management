package com.stock.stock_management.application.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientHistoryResponse {

    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String brand;
}