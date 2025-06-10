package com.example.shop.service;

import com.example.shop.model.Product;
import java.math.BigDecimal;
import java.util.Map;

public interface Promotion {
    /**
     * Calculate the total price for the given items under this promotion.
     * @param items  a map of Product → quantity
     * @return discounted total
     */
    BigDecimal apply(Map<Product,Integer> items);
}
