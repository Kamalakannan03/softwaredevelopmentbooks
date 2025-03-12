package org.bnp.service;

import org.bnp.domain.Book;
import org.bnp.domain.CartItem;
import org.bnp.model.GroupedBook;
import org.bnp.model.GroupedBookDiscount;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupedBookOptimizerTest {
    private GroupedBookDiscountCalculatorService groupedBookDiscountCalculatorService;
    private GroupedBooksGeneratorService groupedBooksGeneratorService;
    private GroupedBookOptimizer groupedBookOptimizer;
    private CartService cartService;
    Book cleanCode;
    Book theCleanCoder;
    Book cleanArchitecture;
    Book testDrivenDevelopmentByExample;
    Book workingEffectivelyWithLegacyCode;
    @Before
    public void setUp() {
        List<GroupedBookDiscount> discounts = List.of(
                new GroupedBookDiscount(2, 5),   // 2 different books → 5% discount
                new GroupedBookDiscount(3, 10),  // 3 different books → 10% discount
                new GroupedBookDiscount(4, 20),  // 4 different books → 20% discount
                new GroupedBookDiscount(5, 25)   // 5 different books → 25% discount
        );

        cleanCode = new Book("Clean Code");
        theCleanCoder = new Book("The Clean Coder");
        cleanArchitecture = new Book("Clean Architecture");
        testDrivenDevelopmentByExample = new Book("Test Driven Development by Example");
        workingEffectivelyWithLegacyCode = new Book("Working Effectively With Legacy Code");

        groupedBookDiscountCalculatorService = new GroupedBookDiscountCalculatorService(discounts);
        groupedBooksGeneratorService = new GroupedBooksGeneratorService(groupedBookDiscountCalculatorService);
        groupedBookOptimizer = new GroupedBookOptimizer(groupedBookDiscountCalculatorService, groupedBooksGeneratorService);

        cartService = new CartService(new PriceCalculatorImpl(groupedBookOptimizer));
    }
    @Test
    public void testgetUniqueGroupedBooksWithMaxDiscount_SingleBook() {
        CartItem item1 = new CartItem(cleanCode, 1);

        List<GroupedBook> result = groupedBookOptimizer.getUniqueGroupedBooksWithMaxDiscount(Arrays.asList(item1));

        assertEquals(1, result.size(), "Should create one set");
        assertEquals(1, result.get(0).getBooks().size(), "Set should contain one book");
    }

    @Test
    public void testBookOfOneCopyWithNoDiscount(){
        cartService.add(cleanCode);
        assertThat(cartService.getTotalPrice(), is(50.0));

    }

    @Test
    public void testBookOfTwoCopyWithNoDiscount(){
        cartService.add(cleanCode);
        cartService.add(cleanCode);
        assertThat(cartService.getTotalPrice(), is(100.0));

    }

    @Test
    public void testBookOfTwoDifferentCopiesWithFivePercentDiscount(){

        cartService.add(cleanCode);
        cartService.add(theCleanCoder);

        assertThat(cartService.getTotalPrice(), is(95.0));

    }

    @Test
    public void testBookOfThreeDifferentCopiesWithTenPercentDiscount(){

        cartService.add(cleanCode);
        cartService.add(theCleanCoder);
        cartService.add(cleanArchitecture);

        assertThat(cartService.getTotalPrice(), is(135.0));

    }


    @Test
    public void testBookOfFourDifferentCopiesWithTwentyPercentDiscount(){

        cartService.add(cleanCode);
        cartService.add(theCleanCoder);
        cartService.add(cleanArchitecture);
        cartService.add(testDrivenDevelopmentByExample);

        assertThat(cartService.getTotalPrice(), is(160.0));

    }

    @Test
    public void testBookOfFiveDifferentCopiesWithTwentyFivePercentDiscount(){

        cartService.add(cleanCode);
        cartService.add(theCleanCoder);
        cartService.add(cleanArchitecture);
        cartService.add(testDrivenDevelopmentByExample);
        cartService.add(workingEffectivelyWithLegacyCode);

        assertThat(cartService.getTotalPrice(), is(187.50));
    }

    @Test
    public void testBookOfFourWhereThreeBooksAreDifferentWithTenPercentDiscount(){

        cartService.add(cleanCode);
        cartService.add(cleanCode);
        cartService.add(theCleanCoder);
        cartService.add(cleanArchitecture);

        assertThat(cartService.getTotalPrice(), is(185.0));

    }

    @Test
    public void testBookOfEightWhereFourBooksAreDifferentWithFortyPercentDiscount(){

        cartService.add(cleanCode);
        cartService.add(cleanCode);
        cartService.add(theCleanCoder);
        cartService.add(theCleanCoder);
        cartService.add(cleanArchitecture);
        cartService.add(cleanArchitecture);
        cartService.add(testDrivenDevelopmentByExample);
        cartService.add(workingEffectivelyWithLegacyCode);

        assertThat(cartService.getTotalPrice(), is(320.0));

    }

}