package com.example.shop.service;

import com.example.shop.model.Category;
import com.example.shop.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PercentageDiscountPromotionTest {

    @Test
    void givenCartWithTotal100_whenApply10PercentPromotion_thenTotalIs90() {
        // given
        Cart cart = new Cart();
        cart.add(new Product("A", BigDecimal.valueOf(30), Category.OWOCE, true));
        cart.add(new Product("B", BigDecimal.valueOf(70), Category.NAPOJE, true));
        String promo = "10PERCENT";

        // when
        cart.applyPromotion(promo);

        // then
        assertEquals(BigDecimal.valueOf(100 * 0.90).setScale(2, RoundingMode.HALF_UP), cart.totalPrice());
    }
}
