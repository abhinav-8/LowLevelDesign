package org.example.lld.ParkingLot.factory;

import org.example.lld.ParkingLot.model.Ticket;
import org.example.lld.ParkingLot.strategy.ParkingFee.DefaultParkingFeeStrategy;
import org.example.lld.ParkingLot.strategy.ParkingFee.ParkingFeeStrategy;
import org.example.lld.ParkingLot.strategy.ParkingFee.PremiumParkingFeeStrategy;

import java.util.Calendar;

public class ParkingFeeFactory {
    public static ParkingFeeStrategy getParkingFeeStrategy(Ticket ticket) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ticket.getEntryTime());
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if(day == Calendar.SATURDAY || day == Calendar.SUNDAY || (hour >= 8 && hour <= 12) || (hour >= 17 && hour <= 20)) {
            return new PremiumParkingFeeStrategy();
        }
        return new DefaultParkingFeeStrategy();
    }
}
