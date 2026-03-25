package org.example.lld.ParkingLot.service;

import org.example.lld.ParkingLot.factory.ParkingFeeFactory;
import org.example.lld.ParkingLot.factory.PaymentModeFactory;
import org.example.lld.ParkingLot.model.*;

import java.util.*;

public class ParkingLotService {
    private final ParkingLot parkingLot;
    private final Map<String, Ticket> activeTicketMap;

    public ParkingLotService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        activeTicketMap = new HashMap<>();
    }

    public Ticket park(Vehicle vehicle) {

        for(ParkingFloor floor : parkingLot.getParkingFloorList()) {

            List<ParkingSpot> availableParkingSpots = floor.findSpot(vehicle);

            System.out.println("Available Parking Spots: " + availableParkingSpots);
            if(!availableParkingSpots.isEmpty()) {
                for(ParkingSpot parkingSpot : availableParkingSpots){
                    parkingSpot.setOccupied(true);
                    parkingSpot.setVehicle(vehicle);
                    floor.getDisplayBoard().decrement(parkingSpot.getSpotType());
                }
                Ticket ticket = new Ticket(UUID.randomUUID().toString(), new Date(), vehicle, availableParkingSpots);
                activeTicketMap.put(ticket.getId(), ticket);

                floor.getDisplayBoard().printDisplayBoard(floor.getFloorNumber());
                return ticket;
            }
        }
        return null;
    }

    public double exit(String ticketId, String paymentMode) {
        Ticket ticket = activeTicketMap.get(ticketId);
        if(ticket == null){
            throw new RuntimeException("Ticket Id is not valid!");
        }

        for (ParkingSpot spot : ticket.getParkingSpot()) {
            spot.setOccupied(false);
            spot.setVehicle(null);

            for (ParkingFloor floor : parkingLot.getParkingFloorList()) {
                if (floor.getFloorNumber() == spot.getFloorNumber()) {
                    floor.getDisplayBoard().increment(spot.getSpotType());
                    floor.getDisplayBoard().printDisplayBoard(floor.getFloorNumber());
                }
            }
        }

        PaymentMode mode = PaymentMode.valueOf(paymentMode);
        double baseFare = ParkingFeeFactory.getParkingFeeStrategy(ticket).calculate(ticket);
        System.out.println("Base Fare: " + baseFare + " for Vehicle: " + ticket.getVehicle());
        double finalFare = PaymentModeFactory.getPaymentStrategy(mode).calculateAmount(baseFare);
        System.out.println("Final Fare: " + finalFare + " for Vehicle: " + ticket.getVehicle());

        activeTicketMap.remove(ticketId);
        return finalFare;
    }
}
