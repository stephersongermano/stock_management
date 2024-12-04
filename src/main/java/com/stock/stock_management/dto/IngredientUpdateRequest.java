package com.stock.stock_management.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientUpdateRequest {

    private BigDecimal price;
    private Integer quantity;

}
