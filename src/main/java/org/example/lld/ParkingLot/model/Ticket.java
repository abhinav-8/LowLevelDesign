package org.example.lld.ParkingLot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Ticket {
    private String id;
    private Date entryTime;
    private Vehicle vehicle;
    private List<ParkingSpot> parkingSpot;
}
