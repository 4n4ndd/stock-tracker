package com.project.stock_tracker.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockResponse {
    private String symbol;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal price;
    private Long volume;
    private LocalDate lastTradingDay;
    private BigDecimal previousClose;
    private BigDecimal change;
    private BigDecimal changePercentage;
}