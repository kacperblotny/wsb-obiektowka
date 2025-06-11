package com.example.shop.service;

import com.example.shop.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface Promotion {
    /**
     * Oblicz cenę uwzględniając przedmioty objęte promocją.
     *
     * @param items mapa Product → ilość
     * @return całość po zniżkach
     */
    BigDecimal apply(Map<Product, Integer> items);
}
