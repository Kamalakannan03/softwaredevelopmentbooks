package org.bnp.model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class GroupedBookDiscountCalculatorService {
    private GroupedBookDiscountCalculatorService discountCalculator;
    @BeforeEach
    void setUp() {
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
}
