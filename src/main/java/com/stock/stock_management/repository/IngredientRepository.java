package com.stock.stock_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.stock_management.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByName(String name);

}
