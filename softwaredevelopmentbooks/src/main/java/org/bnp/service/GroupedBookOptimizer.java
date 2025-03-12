package org.bnp.service;

import lombok.AllArgsConstructor;
import org.bnp.domain.CartItem;
import org.bnp.model.GroupedBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@AllArgsConstructor
public class GroupedBookOptimizer {
    private final GroupedBookDiscountCalculatorService groupedBookDiscountCalculatorService;
    private final GroupedBooksGeneratorService groupedBooksGeneratorService;

    public List<GroupedBook> getUniqueGroupedBooksWithMaxDiscount(List<CartItem> cartItemList) {
        return getBestBookSetCombination(cartItemList);
    }

    private List<GroupedBook> getBestBookSetCombination(List<CartItem> cartItemList) {
        List<List<GroupedBook>> possibleCombinations = new ArrayList<>();
        for (int setSize = cartItemList.size(); setSize >= 1; setSize--) {
            possibleCombinations.add(groupedBooksGeneratorService.generateGroupedBooks(cartItemList, setSize));
        }
        return selectBestDiscountCombination(possibleCombinations);
    }

    private List<GroupedBook> selectBestDiscountCombination(List<List<GroupedBook>> combinations) {
        return combinations.stream()
                .max(Comparator.comparingInt(booksSets -> groupedBookDiscountCalculatorService.getTotalDiscount(booksSets)))
                .orElse(Collections.emptyList());
    }
}
