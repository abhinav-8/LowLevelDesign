package org.example.lld.ParkingLot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class ParkingLot {
    private List<ParkingFloor> parkingFloorList;
}
