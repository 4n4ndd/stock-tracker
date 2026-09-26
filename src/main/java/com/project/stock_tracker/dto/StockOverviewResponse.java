package com.project.stock_tracker.dto;

import lombok.*;
import org.springframework.context.annotation.Primary;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockOverviewResponse {
    private String symbol;
    private String name;
    private String description;
    private String sector;
    private String industry;
    private String marketCap;
    private String peRatio;
    private String dividendYield;
}
