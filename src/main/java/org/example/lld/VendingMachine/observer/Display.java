package org.example.lld.VendingMachine.observer;

public class Display implements Observer {

    @Override
    public void update(String message) {
        System.out.println(message);
    }
}
