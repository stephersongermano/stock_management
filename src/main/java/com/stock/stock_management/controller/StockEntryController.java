package com.stock.stock_management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stock.stock_management.dto.StockEntryRequest;
import com.stock.stock_management.service.StockEntryService;

@RestController
@RequestMapping("/stock-entry")
public class StockEntryController {

    private final StockEntryService stockEntryService;

    public StockEntryController(StockEntryService stockEntryService) {
        this.stockEntryService = stockEntryService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StockEntryRequest request) {
        var response = this.stockEntryService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@RequestParam Long id) {
        var response = this.stockEntryService.findById(id);
        return ResponseEntity.ok(response);
    }
}
