package org.example.lld.ParkingLot.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParkingSpot {
    private int id;
    private SpotType spotType;
    private boolean isOccupied;
    private Vehicle vehicle;
    private int floorNumber;

    public ParkingSpot(int id,  SpotType spotType, int floorNumber) {
        this.id = id;
        this.spotType = spotType;
        this.isOccupied = false;
        this.floorNumber = floorNumber;
    }

    public boolean canFit(VehicleType type) {
        if(type == VehicleType.BIKE)
            return spotType == SpotType.MEDIUM || spotType == SpotType.SMALL;
        if(type == VehicleType.CAR)
            return spotType == SpotType.MEDIUM || spotType == SpotType.LARGE;
        if(type == VehicleType.SMALL_TRUCK || type == VehicleType.BIG_TRUCK)
            return spotType == SpotType.LARGE;
        return false;
    }
}
