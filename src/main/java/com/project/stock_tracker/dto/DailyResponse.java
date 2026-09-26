package com.project.stock_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyResponse {
    private LocalDate date;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private Long volume;



//    public DailyResponse(String date, double v, double v1, double v2, double v3, long l) {
//   }
}
