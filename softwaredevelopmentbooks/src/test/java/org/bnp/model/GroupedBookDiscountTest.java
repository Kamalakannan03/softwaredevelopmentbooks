package org.bnp.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GroupedBookDiscountTest {
    @Test
    public void testGroupedBookDiscount() {
        GroupedBookDiscount groupedBookDiscount = new GroupedBookDiscount(2,5);//this should be unique book and discount %
        assertEquals(2, groupedBookDiscount.getUniqueBooks());
        assertEquals(5, groupedBookDiscount.getDiscount());
    }
}
