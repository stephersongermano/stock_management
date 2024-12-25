package com.stock.stock_management.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.stock.stock_management.dto.IngredientRequest;
import com.stock.stock_management.dto.IngredientResponse;
import com.stock.stock_management.entity.Ingredient;

@Component
public class IngredientMapper {

    public Ingredient toIngredient(IngredientRequest request) {
        return new Ingredient(request.getName(), request.getPrice(), request.getQuantity(), request.getBrand());
    }

    public IngredientResponse toIngredientResponse(Ingredient ingredient) {
        return new IngredientResponse(ingredient.getId(), ingredient.getName(), ingredient.getBrand(),
                ingredient.getPrice(),
                ingredient.getQuantity());
    }

    public static List<Ingredient> toDomainList(List<IngredientRequest> requestToIngredientList) {
        return requestToIngredientList.stream()
                .map(Ingredient::new)
                .collect(Collectors.toList());

    }

    public static List<IngredientResponse> toResponseList(List<Ingredient> ingredientListResponse) {
        return ingredientListResponse.stream()
                .map(ingredient -> new IngredientResponse(ingredient.getId(), ingredient.getName(),
                        ingredient.getBrand(), ingredient.getPrice(), ingredient.getQuantity()))
                .collect(Collectors.toList());
    }

}
