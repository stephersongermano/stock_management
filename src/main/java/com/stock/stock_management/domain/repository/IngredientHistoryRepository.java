package com.stock.stock_management.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.stock_management.domain.entity.IngredientHistory;

public interface IngredientHistoryRepository extends JpaRepository<IngredientHistory, Long> {

}
