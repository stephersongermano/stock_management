package com.stock.stock_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.stock_management.entity.StockEntry;

public interface StockEntryRepository extends JpaRepository<StockEntry, Long> {

}
