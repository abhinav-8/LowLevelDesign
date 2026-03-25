package org.example.lld.ParkingLot.strategy.ParkingFee;

import org.example.lld.ParkingLot.model.Ticket;
import org.example.lld.ParkingLot.model.VehicleType;

import java.util.Calendar;
import java.util.Date;

public class PremiumParkingFeeStrategy implements ParkingFeeStrategy {

    @Override
    public double calculate(Ticket ticket) {
        long hours = (long) Math.ceil((double) ((new Date()).getTime() - ticket.getEntryTime().getTime())/(1000 * 60 * 60));
        double rate = ticket.getVehicle().getType() == VehicleType.BIG_TRUCK ? 120 : 80;
        return hours * rate * multiplier(ticket.getEntryTime());
    }

    private double multiplier(Date entryTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(entryTime); // ✅ entry time

        int day = cal.get(Calendar.DAY_OF_WEEK);
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        if (day == Calendar.SATURDAY || day == Calendar.SUNDAY)
            return 1.5;

        if ((hour >= 9 && hour <= 10) || (hour >= 17 && hour <= 20))
            return 2.0;

        if(hour == 8 || hour == 12)
            return 1.25;

        return 1.0;
    }
}
