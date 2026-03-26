package org.example.lld.VendingMachine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Slot {
    private int code;
    private Item item;
    private int quantity;

    public boolean isAvailable() {
        return quantity > 0;
    }

    public void decrement() {
        quantity = quantity >= 0 ? quantity - 1 : 0;
    }
}
