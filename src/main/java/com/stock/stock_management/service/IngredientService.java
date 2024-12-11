package com.stock.stock_management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stock.stock_management.dto.IngredientRequest;
import com.stock.stock_management.dto.IngredientResponse;
import com.stock.stock_management.dto.IngredientUpdateRequest;
import com.stock.stock_management.entity.Ingredient;
import com.stock.stock_management.exception.IdNotFoundException;
import com.stock.stock_management.mapper.IngredientMapper;
import com.stock.stock_management.repository.IngredientRepository;

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

    public IngredientResponse create(IngredientRequest request) {
        var ingredient = this.ingredientMapper.toIngredient(request);

        this.ingredientRepository.save(ingredient);

        return this.ingredientMapper.toIngredientResponse(ingredient);
    }

    public IngredientResponse update(Long id, IngredientUpdateRequest request) {
        Ingredient ingredientToUpdate = this.ingredientRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        ingredientToUpdate.updatePriceAndQuantity(request.getPrice(), request.getQuantity());

        this.ingredientRepository.save(ingredientToUpdate);

        return this.ingredientMapper.toIngredientResponse(ingredientToUpdate);
    }

    public void delete(Long id) {

        Ingredient ingredientToDelete = this.ingredientRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        ingredientToDelete.setDeletedAt();

        this.ingredientRepository.save(ingredientToDelete);

        this.ingredientRepository.delete(ingredientToDelete);

        return;

    }

}
