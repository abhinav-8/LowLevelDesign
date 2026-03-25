package org.example.lld.ParkingLot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class DisplayBoard {
    private Map<SpotType, Integer> freeSpots;

    public void printDisplayBoard(int floorNumber) {
        System.out.println("------------------");
        System.out.println("Floor Number: " + floorNumber);
        for(SpotType spotType: freeSpots.keySet()) {
            System.out.println(spotType + ": " + freeSpots.get(spotType));
        }
        System.out.println("------------------");
    }

    public void increment(SpotType spotType) {
        freeSpots.put(spotType, freeSpots.getOrDefault(spotType, 0) + 1);
    }

    public void decrement(SpotType spotType) {
        freeSpots.put(spotType, freeSpots.getOrDefault(spotType, 0) - 1);
    }
}
