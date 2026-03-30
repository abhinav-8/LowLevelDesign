package org.example.lld.RideSharingApplication.model;

import java.util.Date;
import java.util.List;

public class Ride {
    Vehicle vehicle;
    private int availableSeats;
    private List<Booking> bookings;
    private Date startTime;
    private Date endTime;
    private String driverId;
//    private RideStatus
}
