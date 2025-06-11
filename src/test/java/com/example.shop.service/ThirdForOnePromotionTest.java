package com.example.shop.service;

import com.example.shop.model.Category;
import com.example.shop.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThirdForOnePromotionTest {

    @Test
    void givenSevenItems_whenApplyThreeForOne_thenTwoCheapestCostOne() {
        // given
        Cart cart = new Cart();
        for (int i = 1; i <= 7; i++) {
            cart.add(new Product("X" + i, BigDecimal.valueOf(i + 1), Category.MIESO, true));
        }
        String promo = "THIRDFOR1";

        // when
        cart.applyPromotion(promo);

        // then
        // oryginalna suma = 2+3+…+8 = 35
        // dwa najtańsze przedmioty (2 i 3) kosztują 1 każdy => zniżka = (1+1)-(2+3)=-3
        assertEquals(BigDecimal.valueOf(35 - 3), cart.totalPrice());
    }
}
