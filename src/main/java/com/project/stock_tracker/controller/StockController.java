package com.project.stock_tracker.controller;

import com.project.stock_tracker.dto.DailyResponse;
import com.project.stock_tracker.dto.StockOverviewResponse;
import com.project.stock_tracker.dto.StockResponse;
import com.project.stock_tracker.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService service;
    @GetMapping("/{stockSymbol}")
    public StockResponse getStock(@PathVariable String stockSymbol){
        return service.getStock(stockSymbol.toUpperCase());
    }
    @GetMapping("/stockOverview/{symbol}")
    public StockOverviewResponse getStockOverview(@PathVariable String symbol){
        return service.getStockOverview(symbol.toUpperCase());
    }
    @GetMapping("/getHistory/{symbol}")
    public List<DailyResponse> getHistory(@PathVariable String symbol, @RequestParam(defaultValue = "30") int days){
        return service.getHistory(symbol.toUpperCase(),days);
    }
}
