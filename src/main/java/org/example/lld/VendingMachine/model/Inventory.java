package org.example.lld.VendingMachine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private final Map<Integer, Slot> slots = new HashMap<>();

    public void addSlot(Slot slot) {
        slots.put(slot.getCode(), slot);
    }
    public Slot getSlot(int slotNum) {
        return slots.get(slotNum);
    }

    public List<String> getInventoryDetails() {

        List<String> list = new ArrayList<>();

        for (Slot slot : slots.values()) {

            if (slot.getItem() == null) {
                list.add("Code: " + slot.getCode() + " | EMPTY");
            } else {
                list.add(
                        "Code: " + slot.getCode() +
                                " | Item: " + slot.getItem().getName() +
                                " | Price: " + slot.getItem().getPrice() +
                                " | Qty: " + slot.getQuantity() +
                                " | Available: " + slot.isAvailable()
                );
            }
        }

        return list;
    }

}
