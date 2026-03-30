package org.example.lld.RideSharingApplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Booking {
    private String id;
    private Ride ride;
    private User bookedBy;
    private int numberOfSeats;
    private Location startLocation;
    private Location endLocation;
}
