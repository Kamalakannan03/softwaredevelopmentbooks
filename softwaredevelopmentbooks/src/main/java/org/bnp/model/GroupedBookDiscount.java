package org.bnp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GroupedBookDiscount {
    private int uniqueBooks;
    private int discount;
}
