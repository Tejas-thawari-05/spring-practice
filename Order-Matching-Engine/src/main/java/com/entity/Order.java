package com.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;
    private String symbol;
    private String side;  // "BUY" or "SELL"
    private BigDecimal price;
    private int quantity;
    private long timestamp;
}
