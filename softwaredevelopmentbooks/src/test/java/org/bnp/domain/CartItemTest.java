package org.bnp.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartItemTest {
    @Test
    public void testToCreateCartItemWithValidBookAndQuantity() {
        Book book = new Book("Clean Code");
        CartItem cartItem = new CartItem(book, 2);

        assertEquals(book, cartItem.getBook());
        assertEquals(2, cartItem.getQuantity());
    }
    @Test
    public void testCartItemUpdateQuantity() {
        Book book = new Book("The Clean Coder");
        CartItem cartItem = new CartItem(book, 1);

        cartItem.setQuantity(5);
        assertEquals(5, cartItem.getQuantity());
    }
}
