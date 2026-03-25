
## Problem Statement: 
Design a system to manage a multi level parking lot.

The system should support the following requirements: 
- The parking lot has multiple floors, with each floor having rows of parking spots. 
- It can accommodate various vehicle types, such as motorcycles, cars, and buses. Parking spots come in different sizes (e.g., small, medium, large). 
- A large vehicle might require multiple adjacent spots. The system must be able to find and assign the first available spot suitable for a vehicle upon its arrival.  Display boards at the entrance should show real time counts of available spots for each vehicle type.
- A ticket should be generated upon entry, containing unique details like vehicle information, spot number, and entry time. 
- Upon exit, the system must calculate the parking fee based on the duration of the stay and process the payment. 
- Display boards at the entrance should show real time counts of available spots for each vehicle type. 

Also, discuss how you would handle scalability and concurrency for a busy parking lot with many vehicles entering and exiting simultaneously.

# 🚗 Parking Lot LLD – Final Revision Code

---

## 🚀 Driver

```java
public class ParkingLotApplication {

    public static void main(String[] args) {

        List<ParkingFloor> parkingFloors = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            List<ParkingSpot> floorSpots = new ArrayList<>();

            for(int j = 0; j < 5; j++) {
                floorSpots.add(new ParkingSpot(j, SpotType.SMALL, i));
                floorSpots.add(new ParkingSpot(5+j, SpotType.MEDIUM, i));
                floorSpots.add(new ParkingSpot(10+j, SpotType.LARGE, i));
            }

            parkingFloors.add(new ParkingFloor(i, floorSpots));
        }

        ParkingLotService service = new ParkingLotService(new ParkingLot(parkingFloors));

        Vehicle bike = new Vehicle(VehicleType.BIKE, "BIKE-123");
        Vehicle car = new Vehicle(VehicleType.CAR, "CAR-456");
        Vehicle truck = new Vehicle(VehicleType.BIG_TRUCK, "TRUCK-789");

        System.out.println("---- PARKING ----");

        Ticket t1 = service.park(bike);
        Ticket t2 = service.park(car);
        Ticket t3 = service.park(truck);

        System.out.println("---- EXIT ----");

        service.exit(t1.getId(), "UPI");
        service.exit(t2.getId(), "CREDIT_CARD");
        service.exit(t3.getId(), "CASH");
    }
}
```

---

## 🧱 Models

```java
enum VehicleType {
    BIKE, CAR, SMALL_TRUCK, BIG_TRUCK
}

enum SpotType {
    SMALL, MEDIUM, LARGE
}

enum PaymentMode {
    CASH, CREDIT_CARD, DEBIT_CARD, UPI
}
```

---

### Vehicle

```java
class Vehicle {
    private VehicleType type;
    private String licenseNumber;

    public Vehicle(VehicleType type, String licenseNumber) {
        this.type = type;
        this.licenseNumber = licenseNumber;
    }

    public VehicleType getType() { return type; }
}
```

---

### ParkingSpot

```java
class ParkingSpot {
    private int id;
    private SpotType spotType;
    private boolean isOccupied;
    private Vehicle vehicle;
    private int floorNumber;

    public ParkingSpot(int id, SpotType spotType, int floorNumber) {
        this.id = id;
        this.spotType = spotType;
        this.floorNumber = floorNumber;
        this.isOccupied = false;
    }

    public boolean canFit(VehicleType type) {
        if(type == VehicleType.BIKE)
            return spotType == SpotType.SMALL || spotType == SpotType.MEDIUM;

        if(type == VehicleType.CAR)
            return spotType == SpotType.MEDIUM || spotType == SpotType.LARGE;

        return spotType == SpotType.LARGE;
    }

    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean val) { this.isOccupied = val; }
    public void setVehicle(Vehicle v) { this.vehicle = v; }
    public SpotType getSpotType() { return spotType; }
    public int getFloorNumber() { return floorNumber; }
}
```

---

### DisplayBoard

```java
class DisplayBoard {
    private Map<SpotType, Integer> freeSpots = new HashMap<>();

    public void increment(SpotType type) {
        freeSpots.put(type, freeSpots.getOrDefault(type, 0) + 1);
    }

    public void decrement(SpotType type) {
        freeSpots.put(type, freeSpots.getOrDefault(type, 0) - 1);
    }

    public void print(int floor) {
        System.out.println("Floor " + floor + " -> " + freeSpots);
    }
}
```

---

### ParkingFloor

```java
class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> spots;
    private DisplayBoard board = new DisplayBoard();

    public ParkingFloor(int floorNumber, List<ParkingSpot> spots) {
        this.floorNumber = floorNumber;
        this.spots = spots;

        for (ParkingSpot s : spots) {
            board.increment(s.getSpotType());
        }
    }

    public List<ParkingSpot> findSpot(Vehicle v) {
        int required = v.getType() == VehicleType.BIG_TRUCK ? 2 : 1;

        List<ParkingSpot> res = new ArrayList<>();
        int count = 0;

        for (ParkingSpot s : spots) {
            if (!s.isOccupied() && s.canFit(v.getType())) {
                res.add(s);
                count++;
                if (count == required) return res;
            } else {
                res.clear();
                count = 0;
            }
        }
        return res;
    }

    public DisplayBoard getBoard() { return board; }
    public int getFloorNumber() { return floorNumber; }
}
```

---

### ParkingLot

```java
class ParkingLot {
    private List<ParkingFloor> floors;

    public ParkingLot(List<ParkingFloor> floors) {
        this.floors = floors;
    }

    public List<ParkingFloor> getFloors() { return floors; }
}
```

---

### Ticket

```java
class Ticket {
    private String id;
    private Date entryTime;
    private Vehicle vehicle;
    private List<ParkingSpot> spots;

    public Ticket(String id, Date entryTime, Vehicle vehicle, List<ParkingSpot> spots) {
        this.id = id;
        this.entryTime = entryTime;
        this.vehicle = vehicle;
        this.spots = spots;
    }

    public String getId() { return id; }
    public Date getEntryTime() { return entryTime; }
    public Vehicle getVehicle() { return vehicle; }
    public List<ParkingSpot> getSpots() { return spots; }
}
```

---

## 🧠 Strategy – Parking Fee

```java
interface ParkingFeeStrategy {
    double calculate(Ticket ticket);
}
```

```java
class DefaultParkingFeeStrategy implements ParkingFeeStrategy {
    public double calculate(Ticket t) {
        long hrs = (long) Math.ceil((new Date().getTime() - t.getEntryTime().getTime()) / (1000*60*60));
        return hrs * (t.getVehicle().getType() == VehicleType.BIG_TRUCK ? 100 : 60);
    }
}
```

```java
class PremiumParkingFeeStrategy implements ParkingFeeStrategy {
    public double calculate(Ticket t) {
        long hrs = (long) Math.ceil((new Date().getTime() - t.getEntryTime().getTime()) / (1000*60*60));
        return hrs * 150;
    }
}
```

---

## 🏭 Factory

```java
class ParkingFeeFactory {
    public static ParkingFeeStrategy get(Ticket t) {
        Calendar c = Calendar.getInstance();
        c.setTime(t.getEntryTime());

        int day = c.get(Calendar.DAY_OF_WEEK);
        int hour = c.get(Calendar.HOUR_OF_DAY);

        if(day == Calendar.SATURDAY || day == Calendar.SUNDAY || hour >= 8 && hour <= 12)
            return new PremiumParkingFeeStrategy();

        return new DefaultParkingFeeStrategy();
    }
}
```

---

## 💳 Payment Strategy

```java
interface PaymentStrategy {
    double pay(double amount);
}
```

```java
class CashPayment implements PaymentStrategy {
    public double pay(double a) { return a; }
}
```

```java
class CardPayment implements PaymentStrategy {
    public double pay(double a) { return a * 1.02; }
}
```

```java
class UPIPayment implements PaymentStrategy {
    public double pay(double a) { return a * 0.95; }
}
```

```java
class PaymentFactory {
    public static PaymentStrategy get(PaymentMode m) {
        return switch (m) {
            case CASH -> new CashPayment();
            case UPI -> new UPIPayment();
            default -> new CardPayment();
        };
    }
}
```

---

## ⚙️ Service

```java
class ParkingLotService {

    private ParkingLot lot;
    private Map<String, Ticket> active = new HashMap<>();

    public ParkingLotService(ParkingLot lot) {
        this.lot = lot;
    }

    public Ticket park(Vehicle v) {
        for (ParkingFloor f : lot.getFloors()) {

            List<ParkingSpot> spots = f.findSpot(v);

            if (!spots.isEmpty()) {
                for (ParkingSpot s : spots) {
                    s.setOccupied(true);
                    s.setVehicle(v);
                    f.getBoard().decrement(s.getSpotType());
                }

                Ticket t = new Ticket(UUID.randomUUID().toString(), new Date(), v, spots);
                active.put(t.getId(), t);

                f.getBoard().print(f.getFloorNumber());
                return t;
            }
        }
        return null;
    }

    public double exit(String id, String mode) {
        Ticket t = active.get(id);

        for (ParkingSpot s : t.getSpots()) {
            s.setOccupied(false);
            s.setVehicle(null);
        }

        double base = ParkingFeeFactory.get(t).calculate(t);
        double finalAmt = PaymentFactory.get(PaymentMode.valueOf(mode)).pay(base);

        active.remove(id);
        System.out.println("Fare: " + finalAmt);
        return finalAmt;
    }
}
```

