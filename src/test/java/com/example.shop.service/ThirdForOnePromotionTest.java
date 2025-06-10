package com.example.shop.service;

import com.example.shop.model.Category;
import com.example.shop.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ThirdForOnePromotionTest {

    @Test
    void givenSevenItems_whenApplyThreeForOne_thenTwoCheapestCostOne() {
        // given
        Cart cart = new Cart();
        for (int i = 1; i <= 7; i++) {
            cart.add(new Product("X"+i, BigDecimal.valueOf(i+1), Category.MEAT, true));
        }
        String promo = "THIRDFOR1";

        // when
        cart.applyPromotion(promo);

        // then
        // original sum = 2+3+…+8 = 35
        // two cheapest (2 and 3) become 1 each instead of 2+3 => discount = (1+1)-(2+3)=-3
        assertEquals(BigDecimal.valueOf(35 - 3), cart.totalPrice());
    }
}
