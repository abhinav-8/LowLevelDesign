package org.example.lld.VendingMachine.state;

import org.example.lld.VendingMachine.model.Denomination;
import org.example.lld.VendingMachine.service.VendingMachine;

public interface State {
    void insertMoney(VendingMachine vendingMachine, Denomination denomination);
    void selectProduct(VendingMachine vendingMachine, int code);
    void dispense(VendingMachine vendingMachine); //Process
    void refund(VendingMachine vendingMachine);
}
