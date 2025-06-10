package com.example.shop.service;

import com.example.shop.model.Product;
import java.math.BigDecimal;
import java.util.Map;

public class SecondForHalfPromotion implements Promotion {
    @Override
    public BigDecimal apply(Map<Product, Integer> items) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Map.Entry<Product,Integer> e : items.entrySet()) {
            Product p = e.getKey();
            int qty = e.getValue();
            int pairs = qty / 2;
            int remainder = qty % 2;
            // each pair: one at full price + one at half price
            BigDecimal pairTotal = p.getPrice()
                    .add(p.getPrice().multiply(new BigDecimal("0.5")));
            sum = sum.add(pairTotal.multiply(BigDecimal.valueOf(pairs)))
                    .add(p.getPrice().multiply(BigDecimal.valueOf(remainder)));
        }
        return sum;
    }
}
