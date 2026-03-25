package org.example.lld.ParkingLot.strategy.Payment;

public class CardPaymentStrategy implements PaymentStrategy {
    @Override
    public double calculateAmount(double amount) {
        return 1.02 * amount;
    }
}
