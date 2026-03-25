package org.example.lld.ParkingLot.factory;

import org.example.lld.ParkingLot.model.PaymentMode;
import org.example.lld.ParkingLot.strategy.Payment.CardPaymentStrategy;
import org.example.lld.ParkingLot.strategy.Payment.CashPaymentStrategy;
import org.example.lld.ParkingLot.strategy.Payment.PaymentStrategy;
import org.example.lld.ParkingLot.strategy.Payment.UPIPaymentStrategy;

public class PaymentModeFactory {
    public static PaymentStrategy getPaymentStrategy(PaymentMode mode) {
        return switch (mode) {
            case CASH -> new CashPaymentStrategy();
            case DEBIT_CARD, CREDIT_CARD -> new CardPaymentStrategy();
            case UPI -> new UPIPaymentStrategy();
        };
    }
}
