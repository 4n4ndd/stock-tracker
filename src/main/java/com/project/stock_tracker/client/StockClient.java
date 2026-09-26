package com.project.stock_tracker.client;

import com.project.stock_tracker.dto.AlphaVantageHistoryResponse;
import com.project.stock_tracker.dto.AlphaVantageOverviewResponse;
import com.project.stock_tracker.dto.AlphaVantageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class StockClient {
    private final WebClient webClient;

    @Value("${alpha.vantage.api.key}")
    private String apiKey;

    public AlphaVantageResponse getStockQuote(String symbol){
        return webClient.get().uri(uriBuilder -> uriBuilder
                .queryParam("function","GLOBAL_QUOTE")
                .queryParam("symbol",symbol)
                .queryParam("apikey",apiKey)
                .build())
                .retrieve()
                .bodyToMono(AlphaVantageResponse.class)
                .block();
    }

    public AlphaVantageOverviewResponse getStockOverview(String symbol) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .queryParam("function","OVERVIEW")
                        .queryParam("symbol",symbol)
                        .queryParam("apikey",apiKey)
                        .build())
                .retrieve()
                .bodyToMono(AlphaVantageOverviewResponse.class)
                .block();
    }
    public AlphaVantageHistoryResponse getHistory(String symbol, int days){
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .queryParam("function","TIME_SERIES_DAILY")
                        .queryParam("symbol",symbol)
                        .queryParam("apikey",apiKey)
                        .build())
                .retrieve()
                .bodyToMono(AlphaVantageHistoryResponse.class)
                .block();
    }
}
