package com.stock.stock_management.service;

import org.springframework.stereotype.Service;

import com.stock.stock_management.dto.IngredientHistoryRequest;
import com.stock.stock_management.dto.IngredientHistoryResponse;
import com.stock.stock_management.mapper.IngredientHistoryMapper;
import com.stock.stock_management.repository.IngredientHistoryRepository;
import com.stock.stock_management.repository.IngredientRepository;

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

    public IngredientHistoryResponse create(IngredientHistoryRequest request) {
        var ingredientHistory = this.ingredientHistoryMapper.toIngredientHistory(request);

        var ingredientOptional = this.ingredientRepository.findByNameAndBrand(request.getName(), request.getBrand());

        if (ingredientOptional.isPresent()) {
            this.ingredientService.update(ingredientOptional.get().getId(),
                    this.ingredientHistoryMapper.toIngredientUpdateRequest(request));
            ingredientHistory.setIngredient(ingredientOptional.get());
        } else {
            var newIngredient = this.ingredientService
                    .create(this.ingredientHistoryMapper.toIngredientRequest(request));
            ingredientHistory.setIngredient(this.ingredientRepository.findById(newIngredient.getId())
                    .orElseThrow(() -> new RuntimeException("Ingredient creation failed")));
        }

        ingredientHistory = this.ingredientHistoryRepository.save(ingredientHistory);

        return this.ingredientHistoryMapper.toIngredientHistoryResponse(ingredientHistory);

    }

}
