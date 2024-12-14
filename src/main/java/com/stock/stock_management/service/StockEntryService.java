package com.stock.stock_management.service;

import org.springframework.stereotype.Service;

import com.stock.stock_management.dto.StockEntryRequest;
import com.stock.stock_management.dto.StockEntryResponse;
import com.stock.stock_management.mapper.StockEntryMapper;
import com.stock.stock_management.repository.StockEntryRepository;

@Service
public class StockEntryService {

    public final StockEntryRepository stockEntryRepository;
    public final StockEntryMapper stockEntryMapper;

    public StockEntryService(StockEntryRepository stockEntryRepository, StockEntryMapper stockEntryMapper) {
        this.stockEntryRepository = stockEntryRepository;
        this.stockEntryMapper = stockEntryMapper;
    }

    public StockEntryResponse create(StockEntryRequest request) {
        var stockEntry = this.stockEntryMapper.toStockEntry(request);

        this.stockEntryRepository.save(stockEntry);

        return this.stockEntryMapper.toStockEntryResponse(stockEntry);
    }

    public StockEntryResponse findById(Long id) {
        return this.stockEntryRepository.findById(id)
                .map(this.stockEntryMapper::toStockEntryResponse)
                .orElseThrow(() -> new RuntimeException("Stock Entry not found"));
    }
}
