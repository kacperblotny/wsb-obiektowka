package com.example.shop.service;

import com.example.shop.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public class PercentageDiscountPromotion implements Promotion {
    private final BigDecimal factor;

    public PercentageDiscountPromotion(BigDecimal percentOff) {
        this.factor = BigDecimal.ONE.subtract(percentOff);
    }

    @Override
    public BigDecimal apply(Map<Product, Integer> items) {
        BigDecimal total = items.entrySet().stream()
                .map(e -> e.getKey().getPrice()
                        .multiply(BigDecimal.valueOf(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return total.multiply(factor);
    }
}
