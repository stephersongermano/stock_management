package com.stock.stock_management.application.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockEntryRequest {

    private Integer noteNumber;
    private BigDecimal totalValue;
    private List<IngredientHistoryRequest> ingredientsHistory;
}
