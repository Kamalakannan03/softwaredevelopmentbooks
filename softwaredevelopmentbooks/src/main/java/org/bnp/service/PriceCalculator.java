package org.bnp.service;

import org.bnp.domain.CartItem;

import java.util.List;

public interface PriceCalculator {
    Double calculate(List<CartItem> cartItemsList);
}
