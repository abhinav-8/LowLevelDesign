package org.example.lld.VendingMachine.driver;

import lombok.SneakyThrows;
import org.example.lld.VendingMachine.model.Denomination;
import org.example.lld.VendingMachine.model.Inventory;
import org.example.lld.VendingMachine.model.Item;
import org.example.lld.VendingMachine.model.Slot;
import org.example.lld.VendingMachine.observer.Display;
import org.example.lld.VendingMachine.service.VendingMachine;

import java.util.Arrays;

public class VendingMachineApplication {
    @SneakyThrows
    static void main(String[] args) {

        Item coke = new Item(1, 10, "coke");
        Item biscuit = new Item(2, 5, "Parle-G");
        Inventory inventory = new Inventory();
        inventory.addSlot(new Slot(101, coke, 5));
        inventory.addSlot(new Slot(102, biscuit, 2));
        VendingMachine vendingMachine = new VendingMachine(inventory, new Display());

//---------------------------------------------------------------------------------

        vendingMachine.addCash(Arrays.asList(Denomination.TWO, Denomination.TEN, Denomination.FIVE, Denomination.FIVE));

//---------------------------------------------------------------------------------

        vendingMachine.displayInventory();

        vendingMachine.addMoney(Denomination.FIVE);
        vendingMachine.addMoney(Denomination.TEN);

        vendingMachine.selectItem(101);
        Thread.sleep(1000);
        vendingMachine.selectItem(102);
        vendingMachine.refund();
        Thread.sleep(1000);
        vendingMachine.dispense();
        Thread.sleep(5000);

//---------------------------------------------------------------------------------

        vendingMachine.addMoney(Denomination.FIVE);
        vendingMachine.addMoney(Denomination.TEN);
        vendingMachine.selectItem(101);
        Thread.sleep(1000);
        vendingMachine.selectItem(102);
        Thread.sleep(1000);
        vendingMachine.dispense();

//---------------------------------------------------------------------------------
        vendingMachine.addMoney(Denomination.FIVE);
        vendingMachine.addMoney(Denomination.TEN);
        vendingMachine.selectItem(101);
        Thread.sleep(1000);
        vendingMachine.selectItem(102);
        Thread.sleep(1000);
        vendingMachine.dispense();

//---------------------------------------------------------------------------------
        vendingMachine.addMoney(Denomination.FIVE);
        vendingMachine.addMoney(Denomination.TEN);
        vendingMachine.selectItem(101);
        Thread.sleep(1000);
        vendingMachine.selectItem(102);
        Thread.sleep(1000);
        vendingMachine.dispense();
    }
}
