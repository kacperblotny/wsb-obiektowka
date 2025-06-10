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
        cart.add(new Product("A", BigDecimal.valueOf(30), Category.FRUIT, true));
        cart.add(new Product("B", BigDecimal.valueOf(70), Category.DRINK, true));
        String promo = "10PERCENT";

        // when
        cart.applyPromotion(promo);

        // then
        assertEquals(BigDecimal.valueOf(100 * 0.90), cart.totalPrice());
    }
}
