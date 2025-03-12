package org.bnp.service;

import org.bnp.domain.Book;
import org.bnp.model.GroupedBook;
import org.bnp.model.GroupedBookDiscount;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GroupedBookDiscountCalculatorServiceTest {
    private GroupedBookDiscountCalculatorService discountCalculator;
    @Before
    public void setUp() {
        List<GroupedBookDiscount> discounts = List.of(
                new GroupedBookDiscount(2, 5),   // 2 different books → 5% discount
                new GroupedBookDiscount(3, 10),  // 3 different books → 10% discount
                new GroupedBookDiscount(4, 20)   // 4 different books → 20% discount
        );
        discountCalculator = new GroupedBookDiscountCalculatorService(discounts);
    }
    @Test
    public void testGetDiscountWhenBookCountExists() {
        assertEquals(5, discountCalculator.getDiscount(2));
        assertEquals(10, discountCalculator.getDiscount(3));
        assertEquals(20, discountCalculator.getDiscount(4));
    }
    @Test
    public void testGetDiscountWhenBookCountDoesNotExist() {
        assertEquals(0, discountCalculator.getDiscount(5));
        assertEquals(0, discountCalculator.getDiscount(1));
    }
    @Test
    public void testCalculateTotalDiscountWithMultipleGroupedBooks() {
        Book book1 = new Book("Clean Code");
        Book book2 = new Book("The Clean Coder");
        Book book3 = new Book("Clean Architecture");
        Book book4 = new Book("Test Driven Development by Example");
        HashSet<Book> groupedBookSetOne = new HashSet<>();
        groupedBookSetOne.add(book1);
        groupedBookSetOne.add(book2);
        HashSet<Book> groupedBookSetTwo = new HashSet<>();
        groupedBookSetTwo.add(book1);
        groupedBookSetTwo.add(book2);
        groupedBookSetTwo.add(book3);
        HashSet<Book> groupedBookSetThree = new HashSet<>();
        groupedBookSetThree.add(book1);
        groupedBookSetThree.add(book2);
        groupedBookSetThree.add(book3);
        groupedBookSetThree.add(book4);
        List<GroupedBook> groupedBookList = List.of(
                new GroupedBook(groupedBookSetOne,5),  // First set has a 5% discount
                new GroupedBook(groupedBookSetTwo,10),  // Second set has a 10% discount
                new GroupedBook(groupedBookSetThree,20)    // Third set has a 20% discount
        );

        int totalDiscount = discountCalculator.getTotalDiscount(groupedBookList);
        assertEquals(35, totalDiscount);
    }
}
