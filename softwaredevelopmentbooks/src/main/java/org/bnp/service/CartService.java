package org.bnp.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bnp.domain.Book;
import org.bnp.domain.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Getter
@AllArgsConstructor
public class CartService {
    private final List<CartItem> cartItemList = new ArrayList<>();
    private final PriceCalculator priceCalculator;

    public void add(Book book) {
        cartItemList.stream()
                .filter(item -> item.getBook().equals(book))
                .findFirst()
                .ifPresentOrElse(
                        item -> item.setQuantity(item.getQuantity() + 1),
                        () -> cartItemList.add(new CartItem(book, 1))
                );
    }

    public double getTotalPrice(){
        return priceCalculator.calculate(cartItemList);
    }
}
