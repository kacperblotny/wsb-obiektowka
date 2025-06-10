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
        Product apple = new Product("Apple", 1.0, Category.FOOD, true);

        // when
        cart.addProduct(apple);

        // then
        assertEquals(1, cart.getProducts().size());
        assertTrue(cart.getProducts().containsKey(apple));
        assertEquals(1, cart.getProducts().get(apple));
    }

    @Test
    void givenCartWithProduct_whenRemoveProduct_thenProductCountDecreases() {
        // given
        Cart cart = new Cart();
        Product apple = new Product("Apple", 1.0, Category.FOOD, true);
        cart.addProduct(apple);
        cart.addProduct(apple);

        // when
        cart.removeProduct(apple);

        // then
        assertEquals(1, cart.getProducts().get(apple));
    }
}
