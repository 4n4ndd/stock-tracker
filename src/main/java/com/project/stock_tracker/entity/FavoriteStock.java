package com.project.stock_tracker.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="fav_stocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String symbol;
}
