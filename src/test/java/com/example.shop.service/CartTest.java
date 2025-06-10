package com.example.shop.service;

import com.example.shop.model.Category;
import com.example.shop.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void givenEmptyCart_whenAddProduct_thenCartHasOneItem() {
        // given
        Cart cart = new Cart();
        Product apple = new Product("Apple", BigDecimal.valueOf(1.0), Category.FRUIT, true);

        // when
        cart.add(apple);

        // then
        assertEquals(1, cart.getItems().size());
        assertTrue(cart.getItems().containsKey(apple));
        assertEquals(1, cart.getItems().get(apple));
    }

    @Test
    void givenCartWithProduct_whenRemoveProduct_thenProductCountDecreases() {
        // given
        Cart cart = new Cart();
        Product apple = new Product("Apple", BigDecimal.valueOf(1.0), Category.FRUIT, true);
        cart.add(apple);
        cart.add(apple);

        // when
        cart.remove(apple);

        // then
        assertEquals(1, cart.getItems().get(apple));
    }
}
