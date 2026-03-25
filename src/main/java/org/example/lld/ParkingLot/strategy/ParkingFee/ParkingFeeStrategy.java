package org.example.lld.ParkingLot.strategy.ParkingFee;

import org.example.lld.ParkingLot.model.Ticket;

public interface ParkingFeeStrategy {
    double calculate(Ticket ticket);
}
