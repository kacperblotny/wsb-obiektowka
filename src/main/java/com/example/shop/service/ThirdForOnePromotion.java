package com.example.shop.service;

import com.example.shop.model.Product;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ThirdForOnePromotion implements Promotion {
    @Override
    public BigDecimal apply(Map<Product, Integer> items) {
        // flatten to list of individual prices
        List<BigDecimal> prices = items.entrySet().stream()
                .flatMap(e -> Collections.nCopies(e.getValue(), e.getKey().getPrice()).stream())
                .sorted()  // cheapest first
                .collect(Collectors.toList());

        int fullGroups = prices.size() / 3;
        BigDecimal sum = BigDecimal.ZERO;
        // first `fullGroups` items cost 1 each
        for (int i = 0; i < prices.size(); i++) {
            if (i < fullGroups) {
                sum = sum.add(BigDecimal.ONE);
            } else {
                sum = sum.add(prices.get(i));
            }
        }
        return sum;
    }
}
