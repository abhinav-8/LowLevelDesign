import org.example.lld.ParkingLot.model.*;
import org.example.lld.ParkingLot.service.ParkingLotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    private ParkingLotService service;

    // ---------- SETUP ----------
    @BeforeEach
    void setup() {
        List<ParkingFloor> floors = new ArrayList<>();

        for (int i = 1; i <= 2; i++) {
            List<ParkingSpot> spots = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                spots.add(new ParkingSpot(j, SpotType.SMALL, i));
                spots.add(new ParkingSpot(j+3, SpotType.MEDIUM, i));
                spots.add(new ParkingSpot(j+6, SpotType.LARGE, i));
            }

            floors.add(new ParkingFloor(i, spots));
        }

        service = new ParkingLotService(new ParkingLot(floors));
    }

    // ---------- TEST 1 ----------
    @Test
    void testBasicParking() {
        Vehicle car = new Vehicle(VehicleType.CAR, "CAR-1");

        Ticket ticket = service.park(car);

        assertNotNull(ticket);
    }

    // ---------- TEST 2 ----------
    @Test
    void testExitFlow() {
        Vehicle bike = new Vehicle(VehicleType.BIKE, "BIKE-1");

        Ticket ticket = service.park(bike);

        double fare = service.exit(ticket.getId(), "UPI");

        assertTrue(fare > 0);
    }

    // ---------- TEST 3 ----------
    @Test
    void testTruckAllocation() {
        Vehicle truck = new Vehicle(VehicleType.BIG_TRUCK, "TRUCK-1");

        Ticket ticket = service.park(truck);

        assertNotNull(ticket);
        assertEquals(2, ticket.getParkingSpot().size());
    }

    // ---------- TEST 4 ----------
    @Test
    void testNoSpace() {

        // Fill parking
        for (int i = 0; i < 20; i++) {
            service.park(new Vehicle(VehicleType.CAR, "CAR-" + i));
        }

        Ticket ticket = service.park(new Vehicle(VehicleType.CAR, "CAR-X"));

        assertNull(ticket);
    }
}
