package org.example.lld.ParkingLot.strategy.Payment;

public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public double calculateAmount(double amount) {
        return amount;
    }
}
