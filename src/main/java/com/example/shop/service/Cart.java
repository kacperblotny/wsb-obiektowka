package com.example.shop.service;

import com.example.shop.model.Product;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private final Map<Product, Integer> items = new LinkedHashMap<>();

    private Promotion promotion;

    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public void add(Product p) {
        if (p.isAvailable()) {
            items.merge(p, 1, Integer::sum);
            System.out.println("Dodano: " + p.getName());
        } else {
            System.out.println("Produkt niedostępny.");
        }
    }

    public void remove(Product p) {
        items.computeIfPresent(p, (key, qty) -> qty > 1 ? qty - 1 : null);
    }

    public void applyPromotion(String code) {
        switch (code.toUpperCase()) {
            case "10PERCENT" -> promotion = new PercentageDiscountPromotion(new BigDecimal("0.10"));
            case "THIRDFOR1" -> promotion = new ThirdForOnePromotion();
            case "SECONDFORHALF" -> promotion = new SecondForHalfPromotion();
            default -> {
                System.out.println("Nieznany kod promocji.");
                return;
            }
        }
        System.out.println("Promocja '" + code + "' aktywowana.");
    }

    public void printContents() {
        if (items.isEmpty()) {
            System.out.println("Koszyk jest pusty.");
            return;
        }
        items.forEach((p, qty) ->
                System.out.printf("%s, %d szt.%n", p.getName(), qty));
    }

    public BigDecimal totalPrice() {
        if (promotion != null) {
            return promotion.apply(items);
        }
        return items.entrySet().stream()
                .map(e -> e.getKey().getPrice()
                        .multiply(BigDecimal.valueOf(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
