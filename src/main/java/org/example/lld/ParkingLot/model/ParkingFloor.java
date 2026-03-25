package org.example.lld.ParkingLot.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Setter
@Getter
public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> parkingSpotList;
    private DisplayBoard displayBoard;

    public ParkingFloor(int floorNumber, List<ParkingSpot> parkingSpotList) {
        this.floorNumber = floorNumber;
        this.parkingSpotList = parkingSpotList;
        this.displayBoard = new DisplayBoard(new HashMap<>());

        for (ParkingSpot spot : parkingSpotList) {
            displayBoard.increment(spot.getSpotType());
        }
        displayBoard.printDisplayBoard(floorNumber);
    }

    public List<ParkingSpot> findSpot(Vehicle vehicle) {
        int required = vehicle.getType() == VehicleType.BIG_TRUCK ? 2 : 1;
        return getConsecutiveSpots(vehicle.getType(), required);
    }

    private List<ParkingSpot> getConsecutiveSpots(VehicleType vehicleType, int requiredSpots) {
        List<ParkingSpot> spotList = new ArrayList<>();
        int count = 0;
        for(ParkingSpot parkingSpot : parkingSpotList) {
            if(!parkingSpot.isOccupied()) {
                if(!parkingSpot.canFit(vehicleType)) continue;
                spotList.add(parkingSpot);
                count++;
                if(count == requiredSpots) return spotList;
            }
            else {
                count = 0;
                spotList.clear();
            }
        }
        spotList.clear();
        return spotList;
    }
}
