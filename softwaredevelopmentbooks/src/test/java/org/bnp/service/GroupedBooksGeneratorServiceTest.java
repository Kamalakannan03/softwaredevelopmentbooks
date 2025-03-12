package org.bnp.service;

import org.bnp.domain.Book;
import org.bnp.domain.CartItem;
import org.bnp.model.GroupedBook;
import org.bnp.model.GroupedBookDiscount;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GroupedBooksAggregatorServiceTest {
    private GroupedBooksAggregatorService groupedBooksAggregatorService;

    @Before
    public void setUp() {
        List<GroupedBookDiscount> discounts = List.of(
                new GroupedBookDiscount(2, 5),   // 2 different books → 5% discount
                new GroupedBookDiscount(3, 10),  // 3 different books → 10% discount
                new GroupedBookDiscount(4, 20)   // 4 different books → 20% discount
        );
        groupedBooksAggregatorService = new GroupedBooksAggregatorService(new GroupedBookDiscountCalculatorService(discounts));
    }
    @Test
    public void shouldGenerateMultipleGroupedBooksWhenCartHasMoreThanMaxSetSize() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem(new Book("Book A"), 2));
        cartItems.add(new CartItem(new Book("Book B"), 2));
        cartItems.add(new CartItem(new Book("Book C"), 2));

        List<GroupedBook> groupedBooks = groupedBooksAggregatorService.generateGroupedBooks(cartItems, 3);

        assertEquals(2, groupedBooks.size());
        assertEquals(3, groupedBooks.get(0).getBooks().size());
    }

}
