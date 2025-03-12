package org.bnp.service;

import lombok.AllArgsConstructor;
import org.bnp.domain.Book;
import org.bnp.domain.CartItem;

import java.util.List;
@AllArgsConstructor
public class PriceCalculatorImpl implements PriceCalculator{
    private final GroupedBookOptimizer groupedBookOptimizer;
    @Override
    public Double calculate(List<CartItem> shoppingCartItems) {
        return groupedBookOptimizer.getUniqueGroupedBooksWithMaxDiscount(shoppingCartItems)
                .stream()
                .mapToDouble(booksSet -> booksSet.getBooks()
                        .stream()
                        .mapToDouble(Book::getPrice)
                        .sum() * (1.0 - (booksSet.getDiscount() / 100.0))
                )
                .sum();
    }
}
