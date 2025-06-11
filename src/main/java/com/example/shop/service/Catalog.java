package com.example.shop.service;

import com.example.shop.model.Category;
import com.example.shop.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Catalog {
    private final List<Product> products = new ArrayList<>();

    public Catalog() {
        seed();
    }

    // przykładowe produkty w sklepie
    private void seed() {
        products.add(new Product("Jabłko", new BigDecimal("2.50"), Category.OWOCE, true));
        products.add(new Product("Cytryna", new BigDecimal("1.99"), Category.OWOCE, true));
        products.add(new Product("Gruszka", new BigDecimal("3.20"), Category.OWOCE, false));
        products.add(new Product("Mleko", new BigDecimal("4.69"), Category.NABIAL, true));
        products.add(new Product("Masło", new BigDecimal("6.30"), Category.NABIAL, true));
        products.add(new Product("Chleb", new BigDecimal("5.00"), Category.PIECZYWO, true));
    }

    // Wyświetl wszystkie produkty sortując alfabetycznie
    public List<Product> listAllAlpha() {
        return products.stream()
                .sorted(Comparator.comparing(Product::getName,
                        String.CASE_INSENSITIVE_ORDER))        // alphabetical
                .toList();
    }

    // Wyświetl wszystkie dostępne produkty z wybranej kategorii sortując po cenie wzwyż
    public List<Product> listByCategory(Category cat) {
        return products.stream()
                .filter(p -> p.getCategory() == cat && p.isAvailable())
                .sorted(Comparator.comparing(Product::getPrice))
                .toList();
    }
}
