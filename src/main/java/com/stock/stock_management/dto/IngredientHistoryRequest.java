package com.stock.stock_management.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientHistoryRequest {

    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String brand;

}
