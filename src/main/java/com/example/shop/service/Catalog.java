package com.example.shop.service;

import com.example.shop.model.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Catalog {
    private final List<Product> products = new ArrayList<>();

    public Catalog() { seed(); }

    // --- Requirement 2b: predefined items ----------------------------------
    private void seed() {
        products.add(new Product("Jabłko",  new BigDecimal("2.50"), Category.FRUIT, true));
        products.add(new Product("Cytryna", new BigDecimal("1.99"), Category.FRUIT, true));
        products.add(new Product("Gruszka", new BigDecimal("3.20"), Category.FRUIT, false));
        products.add(new Product("Mleko",   new BigDecimal("4.69"), Category.DAIRY,  true));
        products.add(new Product("Masło",   new BigDecimal("6.30"), Category.DAIRY,  true));
        products.add(new Product("Chleb",   new BigDecimal("5.00"), Category.BAKERY, true));
    }

    // --- Requirement 2c: list all, alphabetical ----------------------------
    public List<Product> listAllAlpha() {
        return products.stream()
                .sorted(Comparator.comparing(Product::getName,
                        String.CASE_INSENSITIVE_ORDER))        // alphabetical
                .toList();
    }

    // --- Requirement 2d: by category, price ascending, only available ------
    public List<Product> listByCategory(Category cat) {
        return products.stream()
                .filter(p -> p.getCategory() == cat && p.isAvailable())
                .sorted(Comparator.comparing(Product::getPrice)) // cheapest first
                .toList();
    }
}
