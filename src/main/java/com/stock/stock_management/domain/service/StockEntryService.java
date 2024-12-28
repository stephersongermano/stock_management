package com.stock.stock_management.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stock.stock_management.application.dto.StockEntryRequest;
import com.stock.stock_management.application.dto.StockEntryResponse;
import com.stock.stock_management.application.mapper.StockEntryMapper;
import com.stock.stock_management.domain.entity.IngredientHistory;
import com.stock.stock_management.domain.entity.StockEntry;
import com.stock.stock_management.domain.repository.StockEntryRepository;

@Service
public class StockEntryService {

    public final StockEntryRepository stockEntryRepository;
    public final StockEntryMapper stockEntryMapper;
    public final IngredientHistoryService ingredientHistoryService;

    public StockEntryService(StockEntryRepository stockEntryRepository, StockEntryMapper stockEntryMapper,
            IngredientHistoryService ingredientHistoryService) {
        this.stockEntryRepository = stockEntryRepository;
        this.stockEntryMapper = stockEntryMapper;
        this.ingredientHistoryService = ingredientHistoryService;
    }

    @Transactional
    public StockEntryResponse create(StockEntryRequest request) {
        StockEntry stockEntry = this.stockEntryMapper.toStockEntry(request);

        List<IngredientHistory> ingredientsHistory = request.getIngredientsHistory().stream()
                .map(ingredientHistoryService::create)
                .toList();

        ingredientsHistory.forEach(ingredientHistory -> ingredientHistory.setStockEntry(stockEntry));

        stockEntry.setIngredientsHistory(ingredientsHistory);

        stockEntryRepository.save(stockEntry);

        return this.stockEntryMapper.toStockEntryResponse(stockEntry);
    }

    public StockEntryResponse findById(Long id) {
        return this.stockEntryRepository.findById(id)
                .map(this.stockEntryMapper::toStockEntryResponse)
                .orElseThrow(() -> new RuntimeException("Stock Entry not found"));
    }
}
