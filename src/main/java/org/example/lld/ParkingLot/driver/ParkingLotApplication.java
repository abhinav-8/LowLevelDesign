package org.example.lld.ParkingLot.driver;

import org.example.lld.ParkingLot.model.*;
import org.example.lld.ParkingLot.service.ParkingLotService;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotApplication {

    static void main(String[] args) {

       List<ParkingFloor> parkingFloors = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            List<ParkingSpot> floorSpots = new ArrayList<>();
            for(int j = 0; j < 5; j++) {
                floorSpots.add(new ParkingSpot(j, SpotType.SMALL, i));
                floorSpots.add(new ParkingSpot(5+j, SpotType.MEDIUM, i));
                floorSpots.add(new ParkingSpot(10+j, SpotType.LARGE, i));
            }
            ParkingFloor parkingFloor = new ParkingFloor(i,floorSpots);
            parkingFloors.add(parkingFloor);
        }

        ParkingLotService service = new ParkingLotService(new ParkingLot(parkingFloors));


        Vehicle bike = new Vehicle(VehicleType.BIKE, "BIKE-123");
        Vehicle car = new Vehicle(VehicleType.CAR, "CAR-456");
        Vehicle truck = new Vehicle(VehicleType.BIG_TRUCK, "TRUCK-789");
        Vehicle small_truck = new Vehicle(VehicleType.SMALL_TRUCK, "TRUCK-7891");
        Vehicle small_truck2 = new Vehicle(VehicleType.SMALL_TRUCK, "TRUCK-7893");
        Vehicle truck2 = new Vehicle(VehicleType.BIG_TRUCK, "TRUCK-7892");
        Vehicle car2 = new Vehicle(VehicleType.CAR, "CAR-4561");

        // ---------- PARK ----------
        System.out.println("---- PARKING ----");

        Ticket t1 = service.park(bike);
        Ticket t2 = service.park(car);
        Ticket t3 = service.park(truck);
        Ticket t4 = service.park(small_truck);
        Ticket t5 = service.park(small_truck2);
        Ticket t6 = service.park(truck2);
        Ticket t7 = service.park(car2);


        System.out.println("Bike Ticket: " + t1);
        System.out.println("Car Ticket: " + t2);
        System.out.println("Truck Ticket: " + t3);
        System.out.println("small_truck Ticket: " + t4);
        System.out.println("small_truck2 Ticket: " + t5);
        System.out.println("truck2 Ticket: " + t6);
        System.out.println("car2 Ticket: " + t7);

        // ---------- EXIT ----------
        System.out.println("\n---- EXIT ----");

        double bikeFare = service.exit(t1.getId(), "UPI");
        double carFare = service.exit(t2.getId(), "CREDIT_CARD");
        double truckFare = service.exit(t3.getId(), "CASH");
        double small_truckFare = service.exit(t4.getId(), "UPI");
        double small_truck2Fare = service.exit(t5.getId(), "CASH");
        double truck2Fare = service.exit(t6.getId(), "DEBIT_CARD");
        double car2Fare = service.exit(t7.getId(), "CREDIT_CARD");
    }
}
