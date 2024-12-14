package com.stock.stock_management.dto;

import java.math.BigDecimal;
import java.util.List;

import com.stock.stock_management.entity.Ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockEntryRequest {

    private Integer noteNumber;
    private BigDecimal totalValue;
    private List<IngredientRequest> ingredients;
}
