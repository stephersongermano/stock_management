package com.stock.stock_management.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stock.stock_management.application.dto.IngredientRequest;
import com.stock.stock_management.application.dto.IngredientResponse;
import com.stock.stock_management.application.dto.IngredientUpdateRequest;
import com.stock.stock_management.application.mapper.IngredientMapper;
import com.stock.stock_management.domain.entity.Ingredient;
import com.stock.stock_management.domain.repository.IngredientRepository;
import com.stock.stock_management.shared.exception.IdNotFoundException;

@Service
public class IngredientService {

    public final IngredientRepository ingredientRepository;
    public final IngredientMapper ingredientMapper;

    public IngredientService(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
    }

    public List<IngredientResponse> findAll() {
        return ingredientRepository.findAll()
                .stream()
                .map(this.ingredientMapper::toIngredientResponse)
                .toList();
    }

    public IngredientResponse findById(Long id) {
        return ingredientRepository.findById(id)
                .map(this.ingredientMapper::toIngredientResponse)
                .orElseThrow(() -> new IdNotFoundException(id));
    }

    @Transactional
    public IngredientResponse create(IngredientRequest request) {
        Ingredient ingredient = this.ingredientMapper.toIngredient(request);

        this.ingredientRepository.save(ingredient);

        return this.ingredientMapper.toIngredientResponse(ingredient);
    }

    @Transactional
    public IngredientResponse update(Long id, IngredientUpdateRequest request) {
        Ingredient ingredient = this.ingredientRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        ingredient.updatePriceAndQuantity(request.getPrice(), request.getQuantity());

        this.ingredientRepository.save(ingredient);

        return this.ingredientMapper.toIngredientResponse(ingredient);
    }

    public void delete(Long id) {

        Ingredient ingredientToDelete = this.ingredientRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        ingredientToDelete.markAsDeleted();

        this.ingredientRepository.save(ingredientToDelete);

    }

}