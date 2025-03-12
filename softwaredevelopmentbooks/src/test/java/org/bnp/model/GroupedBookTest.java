package org.bnp.model;

import org.bnp.domain.Book;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GroupedBookTest {
    @Test
    public void testGroupBooksWithDiscount() {
        Book book1 = new Book("Clean Code");
        Book book2 = new Book("The Clean Coder");
        HashSet<Book> groupedBook = new HashSet<>();
        groupedBook.add(book1);
        groupedBook.add(book2);
        GroupedBook groupedBooks = new GroupedBook(groupedBook, 10);
        assertEquals(2, groupedBooks.getBooks().size());
        assertTrue(groupedBooks.getBooks().contains(book1));
        assertTrue(groupedBooks.getBooks().contains(book2));
        assertEquals(10, groupedBooks.getDiscount());
    }
}
