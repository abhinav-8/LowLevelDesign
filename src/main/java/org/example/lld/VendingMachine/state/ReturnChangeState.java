package org.example.lld.VendingMachine.state;

import org.example.lld.VendingMachine.model.Denomination;
import org.example.lld.VendingMachine.service.VendingMachine;

public class ReturnChangeState implements State{

    @Override
    public void insertMoney(VendingMachine vendingMachine, Denomination denomination) {
        vendingMachine.notifyObservers("[RETURN_CHANGE] Money insertion not allowed");
    }

    @Override
    public void selectProduct(VendingMachine vendingMachine, int code) {
        vendingMachine.notifyObservers("[RETURN_CHANGE] Product Selection not allowed");
    }

    @Override
    public void dispense(VendingMachine vendingMachine) {

        vendingMachine.displayInventory();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Jo bhi paisa aaya usko overall cash me mark karlo
        vendingMachine.addCash(vendingMachine.getInserted());

        //Jo paisa change me dena hai usse overall cash se minus kar do
        int change = vendingMachine.deduct(vendingMachine.getChangeMap());

        vendingMachine.notifyObservers("[RETURN_CHANGE] Returning Change: " + change + "  " + vendingMachine.getChangeMap() );
        vendingMachine.reset();
        vendingMachine.setState(new IdleState());
    }

    @Override
    public void refund(VendingMachine vendingMachine) {
        vendingMachine.notifyObservers("[RETURN_CHANGE] Refund not allowed");
    }
}
