package org.bnp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bnp.domain.Book;

import java.util.HashSet;

@Getter
@AllArgsConstructor
public class GroupedBook {
    private HashSet<Book> books;
    private int discount;
}
