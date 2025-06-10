package com.example.shop.service;

import com.example.shop.model.Category;
import com.example.shop.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ThreeForOnePromotionTest {

    @Test
    void givenSevenItems_whenApplyThreeForOne_thenTwoCheapestCostOne() {
        // given
        Cart cart = new Cart();
        for (int i = 0; i < 7; i++) {
            cart.addProduct(new Product("X"+i, i+1, Category.OTHER, true));
        }
        ThreeForOnePromotion promo = new ThreeForOnePromotion("3FOR1");

        // when
        cart.applyPromotion(promo);

        // then
        // original sum = 1+2+…+7 = 28
        // two cheapest (1 and 2) become 1 each instead of 1+2 => discount = 2
        assertEquals(28 - 2, cart.calculateTotalPrice(), 0.001);
    }
}
