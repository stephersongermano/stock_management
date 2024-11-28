package com.stock.stock_management.mapper;

import org.springframework.stereotype.Component;

import com.stock.stock_management.dto.IngredientRequest;
import com.stock.stock_management.dto.IngredientResponse;
import com.stock.stock_management.entity.Ingredient;

@Component
public class IngredientMapper {

    public Ingredient toIngredient(IngredientRequest request) {
        return new Ingredient(request.getName(), request.getPrice(), request.getQuantity());
    }

    public IngredientResponse toIngredientResponse(Ingredient ingredient) {
        return new IngredientResponse(ingredient.getId(), ingredient.getName(), ingredient.getPrice(),
                ingredient.getQuantity());
    }

}
