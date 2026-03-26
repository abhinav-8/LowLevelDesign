package org.example.lld.VendingMachine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Item {
    int id;
    int price;
    String name;
}
