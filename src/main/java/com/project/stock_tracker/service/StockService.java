package com.project.stock_tracker.service;

import com.project.stock_tracker.client.StockClient;
import com.project.stock_tracker.dto.*;
import com.project.stock_tracker.entity.FavoriteStock;
import com.project.stock_tracker.repository.FavoriteStockRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockClient client;
    private final FavoriteStockRepo repo;
@Cacheable(value = "stocks", key = "#stockSymbol")
    public StockResponse getStock(String stockSymbol) {
        AlphaVantageResponse response = client.getStockQuote(stockSymbol);

        return StockResponse.builder()
                .symbol(response.globalQuote().symbol())
                .open(new BigDecimal(response.globalQuote().open()))
                .high(new BigDecimal(response.globalQuote().high()))
                .low(new BigDecimal(response.globalQuote().low()))
                .price(new BigDecimal(response.globalQuote().price()))
                .volume(Long.valueOf(response.globalQuote().volume()))
                .lastTradingDay(LocalDate.parse(response.globalQuote().lastTradingDay()))
                .previousClose(new BigDecimal(response.globalQuote().previousClose()))
                .change(new BigDecimal(response.globalQuote().change()))
                .changePercentage(new BigDecimal(
                        response.globalQuote().changePercentage().replace("%", "")
                ))
                .build();
    }

    public StockOverviewResponse getStockOverview(String symbol) {
        AlphaVantageOverviewResponse response = client.getStockOverview(symbol);

        return StockOverviewResponse.builder()
                .symbol(response.symbol())
                .name(response.name())
                .description(response.description())
                .sector(response.sector())
                .industry(response.industry())
                .marketCap(response.marketCap())
                .peRatio(response.peRatio())
                .dividendYield(response.dividendYield())
                .build();
    }

    public List<DailyResponse> getHistory(String symbol, int days) {
        AlphaVantageHistoryResponse response = client.getHistory(symbol, days);

        return response.timeSeries().entrySet().stream()
                .limit(days)
                .map(entry -> {
                    var date = entry.getKey();
                    var daily = entry.getValue();

                    return new DailyResponse(
                            LocalDate.parse(date),
                            Double.parseDouble(daily.open()),
                            Double.parseDouble(daily.close()),
                            Double.parseDouble(daily.high()),
                            Double.parseDouble(daily.low()),
                            Long.parseLong(daily.volume())
                    );
                })
                .collect(Collectors.toList());
    }
    @Transactional
    public FavoriteStock addFavourite(String symbol) {
        if(repo.existsBySymbol(symbol)){
            throw new RuntimeException("Fav stock already exists : " + symbol);
        }
        FavoriteStock favorite = FavoriteStock.builder()
                .symbol(symbol)
                .build();
        return repo.save(favorite);
    }

    public List<StockResponse> getFavouritesWithPrices() {
        List<FavoriteStock> favorites = repo.findAll();
        return favorites.stream()
                .map(fav->getStock(fav.getSymbol()))
                .collect(Collectors.toList());
    }
}