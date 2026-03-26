package org.example.lld.VendingMachine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum Denomination {
    ONE(1), TWO(2), FIVE(5), TEN(10);

    private final int value;
}
