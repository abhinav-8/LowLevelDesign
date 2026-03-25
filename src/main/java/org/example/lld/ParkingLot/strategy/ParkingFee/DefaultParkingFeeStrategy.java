package org.example.lld.ParkingLot.strategy.ParkingFee;

import org.example.lld.ParkingLot.model.Ticket;
import org.example.lld.ParkingLot.model.VehicleType;

import java.util.Date;

public class DefaultParkingFeeStrategy implements ParkingFeeStrategy {

    @Override
    public double calculate(Ticket ticket) {
        long hours = (long) Math.ceil((double) ((new Date()).getTime() - ticket.getEntryTime().getTime())/(1000 * 60 * 60));
        double rate = ticket.getVehicle().getType() == VehicleType.BIG_TRUCK ? 100 : 60;
        return hours * rate;
    }
}
