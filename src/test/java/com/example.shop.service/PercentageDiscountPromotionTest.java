package com.example.shop.service;

import com.example.shop.model.Category;
import com.example.shop.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PercentageDiscountPromotionTest {

    @Test
    void givenCartWithTotal100_whenApply10PercentPromotion_thenTotalIs90() {
        // given
        Cart cart = new Cart();
        cart.addProduct(new Product("A", 30, Category.OTHER, true));
        cart.addProduct(new Product("B", 70, Category.OTHER, true));
        PercentageDiscountPromotion promo = new PercentageDiscountPromotion("10OFF");

        // when
        cart.applyPromotion(promo);

        // then
        assertEquals(90.0, cart.calculateTotalPrice(), 0.001);
    }
}
