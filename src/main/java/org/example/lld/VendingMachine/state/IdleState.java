package org.example.lld.VendingMachine.state;

import org.example.lld.VendingMachine.model.Denomination;
import org.example.lld.VendingMachine.service.VendingMachine;

public class IdleState implements State {

    @Override
    public void insertMoney(VendingMachine vendingMachine, Denomination denomination) {
        vendingMachine.getInserted().add(denomination);
        vendingMachine.notifyObservers("[IDLE] Money Inserted: " + denomination.getValue());
        vendingMachine.setState(new AcceptingMoneyState());
    }

    @Override
    public void selectProduct(VendingMachine vendingMachine, int code) {
        vendingMachine.notifyObservers("[IDLE] Product Selection Not Allowed");
    }

    @Override
    public void dispense(VendingMachine vendingMachine) {
        vendingMachine.notifyObservers("[IDLE] Product Dispense Not Allowed");
    }

    @Override
    public void refund(VendingMachine vendingMachine) {
        vendingMachine.notifyObservers("[IDLE] No Money to Refund");
    }
}
