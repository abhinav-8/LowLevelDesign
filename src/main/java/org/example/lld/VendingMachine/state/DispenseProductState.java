package org.example.lld.VendingMachine.state;

import org.example.lld.VendingMachine.model.Denomination;
import org.example.lld.VendingMachine.model.Slot;
import org.example.lld.VendingMachine.service.VendingMachine;

public class DispenseProductState implements State{

    @Override
    public void insertMoney(VendingMachine vendingMachine, Denomination denomination) {
        vendingMachine.notifyObservers("[DISPENSING] Money insertion not allowed");
    }

    @Override
    public void selectProduct(VendingMachine vendingMachine, int code) {
        vendingMachine.notifyObservers("[DISPENSING] Product selection not allowed");
    }

    @Override
    public void dispense(VendingMachine vendingMachine) {
        Slot slot = vendingMachine.getInventory().getSlot(vendingMachine.getSelectedSlot());
        slot.decrement();
        vendingMachine.notifyObservers("[DISPENSING] "+ slot.getItem().getName() + " dispensed");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        vendingMachine.setState(new ReturnChangeState());
        vendingMachine.getState().dispense(vendingMachine);
    }

    @Override
    public void refund(VendingMachine vendingMachine) {
        vendingMachine.notifyObservers("[DISPENSING] Refund not allowed");
    }
}
