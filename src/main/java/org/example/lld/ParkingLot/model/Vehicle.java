package org.example.lld.ParkingLot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Vehicle {
    private VehicleType type;
    private String licenseNumber;
}
