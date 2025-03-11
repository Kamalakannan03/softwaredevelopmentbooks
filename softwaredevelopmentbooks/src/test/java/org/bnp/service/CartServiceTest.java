package org.bnp.service;


import org.bnp.domain.Book;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartServiceTest {
    @Test
    public void testAddBookToCart() {
        CartService cart = new CartService(totalPrice -> 50.0);
        Book book = new Book("Clean Code");
        cart.add(book);
        assertEquals(50, cart.getTotalPrice(),0.0001);
    }
}
