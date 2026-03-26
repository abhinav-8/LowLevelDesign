package org.example.lld.VendingMachine.state;

import org.example.lld.VendingMachine.model.Denomination;
import org.example.lld.VendingMachine.service.VendingMachine;

public class AcceptingMoneyState implements State {
    @Override
    public void insertMoney(VendingMachine vendingMachine, Denomination denomination) {
        vendingMachine.getInserted().add(denomination);
        vendingMachine.notifyObservers("[ACCEPTING_MONEY] Money Inserted: " + vendingMachine.totalInserted() + " Total Money Inserted: " + vendingMachine.totalInserted());
    }

    @Override
    public void selectProduct(VendingMachine vendingMachine, int code) {
        vendingMachine.setSelectedSlot(code);
        vendingMachine.notifyObservers("[ACCEPTING_MONEY] Selected Slot: " + code);
        vendingMachine.setState(new ItemSelectionState());
    }

    @Override
    public void dispense(VendingMachine vendingMachine) {
        vendingMachine.notifyObservers("[ACCEPTING_MONEY] Product Dispense Not Allowed");
    }

    @Override
    public void refund(VendingMachine vendingMachine) {
        vendingMachine.notifyObservers("[ACCEPTING_MONEY] Refund done of: " + vendingMachine.totalInserted());
        vendingMachine.reset();
        vendingMachine.setState(new IdleState());
    }
}
