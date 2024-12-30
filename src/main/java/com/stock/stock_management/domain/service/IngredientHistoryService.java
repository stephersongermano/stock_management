package com.stock.stock_management.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stock.stock_management.domain.entity.IngredientHistory;
import com.stock.stock_management.domain.repository.IngredientHistoryRepository;
import com.stock.stock_management.domain.repository.IngredientRepository;
import com.stock.stock_management.application.dto.IngredientHistoryRequest;
import com.stock.stock_management.application.dto.IngredientHistoryResponse;
import com.stock.stock_management.application.mapper.IngredientHistoryMapper;

@Service
public class IngredientHistoryService {

    public final IngredientHistoryRepository ingredientHistoryRepository;
    public final IngredientHistoryMapper ingredientHistoryMapper;
    public final IngredientRepository ingredientRepository;
    public final IngredientService ingredientService;

    public IngredientHistoryService(IngredientHistoryRepository ingredientHistoryRepository,
            IngredientHistoryMapper ingredientHistoryMapper, IngredientRepository ingredientRepository,
            IngredientService ingredientService) {
        this.ingredientHistoryRepository = ingredientHistoryRepository;
        this.ingredientHistoryMapper = ingredientHistoryMapper;
        this.ingredientRepository = ingredientRepository;
        this.ingredientService = ingredientService;
    }

    @Transactional
    public IngredientHistoryResponse create(IngredientHistoryRequest request) {
        IngredientHistory ingredientHistory = this.ingredientHistoryMapper.toIngredientHistory(request);

        var existingIngredient = this.ingredientRepository.findByNameAndBrand(request.getName(), request.getBrand());

        existingIngredient.ifPresentOrElse(ingredient -> {
            this.ingredientService.update(ingredient.getId(),
                    this.ingredientHistoryMapper.toIngredientUpdateRequest(request));
            ingredientHistory.setIngredient(ingredient);
        }, () -> {
            var newIngredient = this.ingredientService
                    .create(this.ingredientHistoryMapper.toIngredientRequest(request));
            ingredientHistory.setIngredient(this.ingredientRepository.findById(newIngredient.getId())
                    .orElseThrow(() -> new RuntimeException("Ingredient creation failed")));
        });

        this.ingredientHistoryRepository.save(ingredientHistory);

        return this.ingredientHistoryMapper.toIngredientHistoryResponse(ingredientHistory);
    }

}
