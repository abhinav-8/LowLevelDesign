package org.example.lld.ParkingLot.strategy.Payment;

public class UPIPaymentStrategy implements PaymentStrategy {

    @Override
    public double calculateAmount(double amount) {
        return 0.95 * amount;
    }
}
