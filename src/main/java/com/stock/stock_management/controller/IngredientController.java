package com.stock.stock_management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stock.stock_management.dto.IngredientRequest;
import com.stock.stock_management.service.IngredientService;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        var response = this.ingredientService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@RequestParam Long id) {
        var response = this.ingredientService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody IngredientRequest request) {
        var response = this.ingredientService.create(request);
        return ResponseEntity.ok(response);
    }

}
