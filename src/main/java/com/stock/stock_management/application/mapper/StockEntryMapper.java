package com.stock.stock_management.application.mapper;

import org.springframework.stereotype.Component;

import com.stock.stock_management.application.dto.StockEntryRequest;
import com.stock.stock_management.application.dto.StockEntryResponse;
import com.stock.stock_management.domain.entity.StockEntry;

@Component
public class StockEntryMapper {

    public StockEntry toStockEntry(StockEntryRequest request) {
        return new StockEntry(request.getNoteNumber(), request.getTotalValue(),
                IngredientHistoryMapper.toDomainListIngredientHistory(request.getIngredientsHistory()));
    }

    public StockEntryResponse toStockEntryResponse(StockEntry stockEntry) {
        return new StockEntryResponse(stockEntry.getNoteNumber(), stockEntry.getTotalValue(),
                IngredientHistoryMapper.toResponseListIngredientHistory(stockEntry.getIngredientsHistory()));
    }

}
