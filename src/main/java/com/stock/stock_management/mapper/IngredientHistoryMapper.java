package com.stock.stock_management.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.stock.stock_management.dto.IngredientHistoryRequest;
import com.stock.stock_management.dto.IngredientHistoryResponse;
import com.stock.stock_management.dto.IngredientRequest;
import com.stock.stock_management.dto.IngredientUpdateRequest;
import com.stock.stock_management.entity.IngredientHistory;

@Component
public class IngredientHistoryMapper {

    public IngredientHistory toIngredientHistory(IngredientHistoryRequest request) {
        return new IngredientHistory(request.getName(), request.getPrice(), request.getQuantity(), request.getBrand());
    }

    public static List<IngredientHistory> toDomainListIngredientHistory(
            List<IngredientHistoryRequest> requestToIngredientHistoryList) {
        return requestToIngredientHistoryList.stream()
                .map(IngredientHistory::new)
                .collect(Collectors.toList());
    }

    public IngredientHistoryResponse toIngredientHistoryResponse(IngredientHistory ingredientHistory) {
        return new IngredientHistoryResponse(ingredientHistory.getName(), ingredientHistory.getPrice(),
                ingredientHistory.getQuantity(), ingredientHistory.getBrand());
    }

    public static List<IngredientHistoryResponse> toResponseListIngredientHistory(
            List<IngredientHistory> ingredientHistoryListResponse) {
        return ingredientHistoryListResponse.stream()
                .map(ingredientHistory -> new IngredientHistoryResponse(ingredientHistory.getName(),
                        ingredientHistory.getPrice(), ingredientHistory.getQuantity(), ingredientHistory.getBrand()))
                .collect(Collectors.toList());
    }

    public IngredientRequest toIngredientRequest(IngredientHistoryRequest request) {
        return new IngredientRequest(request.getName(), request.getPrice(), request.getQuantity(), request.getBrand());
    }

    public IngredientUpdateRequest toIngredientUpdateRequest(IngredientHistoryRequest request) {
        return new IngredientUpdateRequest(request.getPrice(), request.getQuantity());
    }

}
