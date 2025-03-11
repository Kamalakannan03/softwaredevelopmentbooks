package org.bnp.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bnp.model.GroupedBookDiscount;

import java.util.List;
@AllArgsConstructor
@Getter
public class GroupedBookDiscountCalculatorService {
    private final List<GroupedBookDiscount> discounts;

    public int getDiscount(int bookCount) {
        return discounts.stream()
                .filter(discount -> discount.getUniqueBooks() == bookCount)
                .map(GroupedBookDiscount::getDiscount)
                .findFirst()
                .orElse(0);
    }
}
