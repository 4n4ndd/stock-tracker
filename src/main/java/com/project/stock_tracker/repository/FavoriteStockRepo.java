package com.project.stock_tracker.repository;

import com.project.stock_tracker.entity.FavoriteStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteStockRepo extends JpaRepository<FavoriteStock,Long> {
    boolean existsBySymbol(String symbol);
}
