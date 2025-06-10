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
        for (int i = 1; i <= 7; i++) {
            cart.add(new Product("X"+i, BigDecimal.valueOf(i), Category.MEAT, true));
        }
        String promo = "3FOR1";

        // when
        cart.applyPromotion(promo);

        // then
        // original sum = 1+2+…+7 = 28
        // two cheapest (1 and 2) become 0 each instead of 1+2 => discount = 3
        assertEquals(BigDecimal.valueOf(28 - 3), cart.totalPrice());
    }
}
