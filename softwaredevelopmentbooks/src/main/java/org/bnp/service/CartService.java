package org.bnp.service;

import lombok.AllArgsConstructor;
import org.bnp.domain.Book;
import org.bnp.domain.CartItem;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
public class CartService {
    private final List<CartItem> cartItemList = new ArrayList<>();
    private final PriceCalculator priceCalculator;

    public void add(Book book){
        cartItemList.add(new CartItem(book,1));
    }
    public double getTotalPrice(){
        return priceCalculator.calculate(cartItemList);
    }
}
