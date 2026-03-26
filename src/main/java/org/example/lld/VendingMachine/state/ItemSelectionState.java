package org.example.lld.VendingMachine.state;

import org.example.lld.VendingMachine.model.Denomination;
import org.example.lld.VendingMachine.model.Slot;
import org.example.lld.VendingMachine.service.VendingMachine;

import java.util.Map;

public class ItemSelectionState implements State {

    @Override
    public void insertMoney(VendingMachine vendingMachine, Denomination denomination) {
        vendingMachine.getInserted().add(denomination);
        vendingMachine.notifyObservers("[ITEM_SELECTION] Total Money Inserted: " + vendingMachine.totalInserted());
    }

    @Override
    public void selectProduct(VendingMachine vendingMachine, int code) {
        vendingMachine.setSelectedSlot(code);
        vendingMachine.notifyObservers("[ITEM_SELECTION] Product Changed: " + code);
    }

    @Override
    public void dispense(VendingMachine vendingMachine) {
        Slot slot = vendingMachine.getInventory().getSlot(vendingMachine.getSelectedSlot());

        if(slot == null || !slot.isAvailable()) {
            vendingMachine.notifyObservers("[ITEM_SELECTION] Not Available");
            return;
        }

        int itemPrice = slot.getItem().getPrice();
        int total = vendingMachine.totalInserted();

        if(itemPrice > total) {
            vendingMachine.notifyObservers("[ITEM_SELECTION] Insufficient Money!");
            return;
        }

        int balance = total - itemPrice;

        Map<Denomination, Integer> change = vendingMachine.getChange(balance);

        if(change == null) {
            vendingMachine.notifyObservers("[ITEM_SELECTION] Cannot exchange money. Refunding.....!");
            refund(vendingMachine);
            return;
        }

        vendingMachine.setChangeMap(change);
        vendingMachine.setState(new DispenseProductState());
        vendingMachine.getState().dispense(vendingMachine);
    }

    @Override
    public void refund(VendingMachine vendingMachine) {
        vendingMachine.notifyObservers("[ITEM_SELECTION] Refund done of: " + vendingMachine.totalInserted());
        vendingMachine.reset();
        vendingMachine.setState(new IdleState());
    }
}
