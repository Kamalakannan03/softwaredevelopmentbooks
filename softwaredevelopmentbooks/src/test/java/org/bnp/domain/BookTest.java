package org.bnp.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void testBookHasTitle() {
        Book book = new Book("Clean Code");
        assertEquals("Clean Code", book.getTitle());
    }
    @Test
    public void testBookHasFixedPrice() {
        Book book = new Book("The Clean Coder");
        assertEquals(50.0, book.getPrice(),0.0001);
    }
    @Test
    public void testBookShouldNotAllowNullTitle() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Book(null));
        assertEquals("title is marked non-null but is null",exception.getMessage());
    }
}
