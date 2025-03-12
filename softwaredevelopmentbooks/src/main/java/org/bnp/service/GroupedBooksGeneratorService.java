package org.bnp.service;

import lombok.AllArgsConstructor;
import org.bnp.domain.Book;
import org.bnp.domain.CartItem;
import org.bnp.model.GroupedBook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@AllArgsConstructor
public class GroupedBooksGeneratorService {
    private final GroupedBookDiscountCalculatorService groupedBookDiscountCalculatorService;
    public List<GroupedBook> generateGroupedBooks(List<CartItem> cartItemList, int maxSetSize) {
        List<CartItem> itemsCopy = cloneCartItems(cartItemList);
        List<GroupedBook> groupedBookList = new ArrayList<>();

        while (!itemsCopy.isEmpty()) {
            groupedBookList.add(createGroupedBook(itemsCopy, maxSetSize));
        }
        return groupedBookList;
    }
    public GroupedBook createGroupedBook(List<CartItem> cartItems, int maxSetSize) {
        HashSet<Book> uniqueBooks = new HashSet<>();

        for (int i = 0; i < cartItems.size() && uniqueBooks.size() < maxSetSize; i++) {
            CartItem cartItem = cartItems.get(i);
            uniqueBooks.add(cartItem.getBook());
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            if (cartItem.getQuantity() == 0) {
                cartItems.remove(i);
                i--;
            }
        }

        return new GroupedBook(uniqueBooks, groupedBookDiscountCalculatorService.getDiscount(uniqueBooks.size()));
    }
    public List<CartItem> cloneCartItems(List<CartItem> cartItemList) {
        List<CartItem> copy = new ArrayList<>();
        for (CartItem item : cartItemList) {
            copy.add(new CartItem(item.getBook(), item.getQuantity()));
        }
        return copy;
    }
}
