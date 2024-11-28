package com.stock.stock_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stock.stock_management.dto.IngredientRequest;
import com.stock.stock_management.dto.IngredientResponse;
import com.stock.stock_management.mapper.IngredientMapper;
import com.stock.stock_management.repository.IngredientRepository;

import jakarta.persistence.EntityNotFoundException;

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
                .orElseThrow(() -> new EntityNotFoundException("Produto com ID " + id + " não encontrado"));
    }

    public IngredientResponse create(IngredientRequest request) {
        var ingredient = this.ingredientMapper.toIngredient(request);

        this.ingredientRepository.save(ingredient);

        return this.ingredientMapper.toIngredientResponse(ingredient);
    }

}
