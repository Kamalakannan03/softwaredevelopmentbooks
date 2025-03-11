package org.bnp.service;


import org.bnp.domain.Book;
import org.bnp.domain.CartItem;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CartServiceTest {

    @Test
    public void testAddBookToCart() {
        CartService cart = new CartService(totalPrice -> 50.0);
        Book book = new Book("Clean Code");
        cart.add(book);
        assertEquals(50, cart.getTotalPrice(),0.0001);
    }

    @Test
    public void testAddSameBookIncreasesQuantity() {
        Book book1 = new Book("Clean Code");
        Book book2 = new Book("The Clean Coder");
        Book book3 = new Book("The Clean Coder");

        CartService cartService = new CartService(totalPrice -> 100.0);
        cartService.add(book1);
        cartService.add(book2);
        cartService.add(book3);
        List<CartItem> items = cartService.getCartItemList();
        assertEquals(2, items.size());
        assertEquals(2, items.get(1).getQuantity());
    }
}
