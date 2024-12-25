package com.stock.stock_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.stock_management.entity.IngredientHistory;

public interface IngredientHistoryRepository extends JpaRepository<IngredientHistory, Long> {

}
