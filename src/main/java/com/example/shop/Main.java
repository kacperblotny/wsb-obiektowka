package com.example.shop;

import com.example.shop.model.Category;
import com.example.shop.model.Product;
import com.example.shop.service.Catalog;
import com.example.shop.service.Cart;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        Cart cart = new Cart();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Pokaż wszystkie produkty");
            System.out.println("2. Pokaż produkty wg kategorii");
            System.out.println("3. Dodaj produkt do koszyka");
            System.out.println("4. Usuń produkt z koszyka");
            System.out.println("5. Pokaż zawartość koszyka");
            System.out.println("6. Zastosuj kod promocji");
            System.out.println("7. Pokaż cenę całkowitą");
            System.out.println("0. Wyjdź");
            System.out.print("Wybór: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.println("== Wszystkie produkty ==");
                    List<Product> all = catalog.listAllAlpha();
                    for (int i = 0; i < all.size(); i++) {
                        System.out.printf("%d) %s%n", i, all.get(i));
                    }
                }
                case 2 -> {
                    System.out.println("Dostępne kategorie:");
                    for (Category c : Category.values()) {
                        System.out.println("- " + c);
                    }
                    System.out.print("Podaj kategorię: ");
                    String cat = sc.nextLine().trim().toUpperCase();
                    try {
                        List<Product> byCat = catalog.listByCategory(Category.valueOf(cat));
                        System.out.println("== " + cat + " ==");
                        for (int i = 0; i < byCat.size(); i++) {
                            System.out.printf("%d) %s%n", i, byCat.get(i));
                        }
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Niepoprawna kategoria.");
                    }
                }
                case 3 -> {
                    System.out.print("Podaj indeks produktu (wg listy z pkt.1): ");
                    int idx = sc.nextInt(); sc.nextLine();
                    List<Product> all = catalog.listAllAlpha();
                    if (idx >= 0 && idx < all.size()) {
                        cart.add(all.get(idx));
                        System.out.println("Dodano: " + all.get(idx).getName());
                    } else {
                        System.out.println("Nieprawidłowy indeks.");
                    }
                }
                case 4 -> {
                    System.out.println("== Zawartość koszyka ==");
                    cart.printContents();
                    System.out.print("Podaj nazwę produktu do usunięcia: ");
                    String nameToRemove = sc.nextLine().trim();  // never reassign this

                    boolean removed = cart.getItems().keySet().stream()
                            .filter(p -> p.getName().equalsIgnoreCase(nameToRemove))
                            .findFirst()
                            .map(p -> {
                                cart.remove(p);
                                return true;
                            })
                            .orElse(false);

                    if (removed) {
                        System.out.println("Usunięto jeden egzemplarz: " + nameToRemove);
                    } else {
                        System.out.println("Nie znaleziono takiego produktu w koszyku.");
                    }
                }
                case 5 -> {
                    System.out.println("== Zawartość koszyka ==");
                    cart.printContents();
                }
                case 6 -> {
                    System.out.print("Podaj kod promocji (10PERCENT, 3FOR1, 2FORHALF): ");
                    String code = sc.nextLine().trim();
                    cart.applyPromotion(code);
                }
                case 7 -> {
                    BigDecimal total = cart.totalPrice();
                    System.out.printf("Cena razem: %.2f zł%n", total);
                }
                case 0 -> {
                    System.out.println("Do zobaczenia!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Niepoprawny wybór.");
            }
        }
    }
}
