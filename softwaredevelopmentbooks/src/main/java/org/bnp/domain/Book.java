package org.bnp.domain;

import lombok.*;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Book {
    @NonNull
    private final String title;
    private final double price = 50;
}
