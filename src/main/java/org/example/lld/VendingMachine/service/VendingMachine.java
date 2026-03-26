package org.example.lld.VendingMachine.service;

import lombok.Getter;
import lombok.Setter;
import org.example.lld.VendingMachine.model.Denomination;
import org.example.lld.VendingMachine.model.Inventory;
import org.example.lld.VendingMachine.observer.Display;
import org.example.lld.VendingMachine.observer.Observer;
import org.example.lld.VendingMachine.state.IdleState;
import org.example.lld.VendingMachine.state.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class VendingMachine {
    private State state;
    private List<Observer> observers = new ArrayList<>();
    private Integer selectedSlot = -1;
    private Map<Denomination, Integer> changeMap = new HashMap<>();
    private Map<Denomination, Integer> cashMap = new HashMap<>();
    private List<Denomination> inserted = new ArrayList<>();
    private Inventory inventory = new Inventory();

    public VendingMachine(Inventory inventory, Observer observer) {
        state = new IdleState();
        addObserver(new Display());
        selectedSlot = -1;
        changeMap = new HashMap<>();
        cashMap = new HashMap<>();
        this.inventory =  inventory;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void addMoney(Denomination denomination) {
        state.insertMoney(this, denomination);
    }

    public void selectItem(Integer slotNumber) {
        state.selectProduct(this, slotNumber);
    }

    public void dispense() {
        state.dispense(this);
    }

    public void refund() {
        state.refund(this);
    }

    public void addCash(List<Denomination> denominations) {
        for (Denomination denomination : denominations) {
            cashMap.put(denomination, cashMap.getOrDefault(denomination, 0) + 1);
        }
    }

    public int deduct(Map<Denomination, Integer> map) {
        int balance = 0;
        for (Denomination d : map.keySet()) {
            cashMap.put(d, cashMap.get(d) - map.get(d));
            balance += d.getValue() * map.get(d);
        }
        return balance;
    }

    public int totalInserted() {
        int total = 0;
        for(Denomination denomination: inserted) {
            total += denomination.getValue();
        }
        return total;
    }

    public void reset() {
        inserted.clear();
        selectedSlot = -1;
        changeMap = new HashMap<>();
        notifyObservers("------------------------------------------------------");
    }

    //Will give incorrect results for now, not optimized , correct would be dp
    public Map<Denomination, Integer> getChange(int amount) {
        Map<Denomination, Integer> res = new HashMap<>();

        Denomination[] values = Denomination.values();

        for (int i = values.length - 1; i >= 0; i--) {
            Denomination d = values[i];
            int count = cashMap.getOrDefault(d, 0);

            while (amount >= d.getValue() && count > 0) {
                amount -= d.getValue();
                count--;
                res.put(d, res.getOrDefault(d, 0) + 1);
            }
        }

        return amount == 0 ? res : null;
    }

    public void displayInventory() {
        notifyObservers("----- INVENTORY -----");
        for (String line : inventory.getInventoryDetails()) {
            notifyObservers(line);
        }
        notifyObservers("---------------------");
    }
}
