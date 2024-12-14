package com.stock.stock_management.mapper;

import org.springframework.stereotype.Component;

import com.stock.stock_management.dto.StockEntryRequest;
import com.stock.stock_management.dto.StockEntryResponse;
import com.stock.stock_management.entity.StockEntry;

@Component
public class StockEntryMapper {

    public StockEntry toStockEntry(StockEntryRequest request) {
        return new StockEntry(request.getNoteNumber(), request.getTotalValue(),
                IngredientMapper.toDomainList(request.getIngredients()));
    }

    public StockEntryResponse toStockEntryResponse(StockEntry stockEntry) {
        return new StockEntryResponse(stockEntry.getNoteNumber(), stockEntry.getTotalValue(), null);
    }

}
