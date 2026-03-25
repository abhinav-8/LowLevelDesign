## Problem Statement:
Design a system to manage elevators in a multi-floor building. The system should support the following requirements:

- The building has multiple floors and multiple elevators. 
- Users can request an elevator from any floor using UP/DOWN buttons. 
- Users inside the elevator can select destination floors.
- The system should provide real-time updates of elevator positions and states (for display panels).
Also, discuss how you would handle scalability and concurrency when multiple users are requesting elevators at the same time.

# 🚀 Elevator System -> FOLLOWED strategy and observer. Factory can be implemented for various types of elevators, command can also be implemented.  

```java
// Enums
enum ElevatorDirection {
    UP, DOWN, IDLE
}

enum ElevatorState {
    MOVING, IDLE, STOPPED
}
```

```java
// Observer
interface ElevatorObserver {
    void onFloorChange(int eId, int floor);
    void onStateChange(int eId, ElevatorState state);
}

class DisplayPanel implements ElevatorObserver {

    @Override
    public void onFloorChange(int eId, int floor) {
        System.out.println("[Elevator " + eId + "] reached floor: " + floor);
    }

    @Override
    public void onStateChange(int eId, ElevatorState state) {
        System.out.println("[Elevator " + eId + "] state: " + state);
    }
}
```

```java
// Strategy
interface SchedulingStrategy {
    Integer getNextStop(Elevator elevator);
}
```

```java
// LOOK Strategy
class LookScheduling implements SchedulingStrategy {

    @Override
    public Integer getNextStop(Elevator elevator) {

        if (elevator.getRequests().isEmpty()) return null;

        int current = elevator.getFloor();

        Integer up = elevator.getRequests().higher(current);
        Integer down = elevator.getRequests().lower(current);

        if (elevator.getDirection() == ElevatorDirection.UP) {
            if (up != null) return up;

            elevator.setDirection(ElevatorDirection.DOWN);
            return down;
        }

        else if (elevator.getDirection() == ElevatorDirection.DOWN) {
            if (down != null) return down;

            elevator.setDirection(ElevatorDirection.UP);
            return up;
        }

        else {
            if (up == null) {
                elevator.setDirection(ElevatorDirection.DOWN);
                return down;
            }

            if (down == null) {
                elevator.setDirection(ElevatorDirection.UP);
                return up;
            }

            if (Math.abs(up - current) < Math.abs(down - current)) {
                elevator.setDirection(ElevatorDirection.UP);
                return up;
            }

            elevator.setDirection(ElevatorDirection.DOWN);
            return down;
        }
    }
}
```

```java
// Elevator
import java.util.*;

class Elevator {

    private int id;
    private int floor;
    private ElevatorState state;
    private ElevatorDirection direction;

    private SchedulingStrategy strategy;
    private TreeSet<Integer> requests;
    private List<ElevatorObserver> observers;

    public Elevator(int id, SchedulingStrategy strategy) {
        this.id = id;
        this.floor = 0;
        this.state = ElevatorState.IDLE;
        this.direction = ElevatorDirection.IDLE;
        this.strategy = strategy;
        this.requests = new TreeSet<>();
        this.observers = new ArrayList<>();
    }

    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
    }

    private void notifyFloorChange() {
        for (ElevatorObserver o : observers) {
            o.onFloorChange(id, floor);
        }
    }

    private void notifyStateChange() {
        for (ElevatorObserver o : observers) {
            o.onStateChange(id, state);
        }
    }

    public void addRequest(int f) {
        requests.add(f);

        if (state == ElevatorState.IDLE) {
            direction = (f > floor) ? ElevatorDirection.UP : ElevatorDirection.DOWN;
            state = ElevatorState.MOVING;
            notifyStateChange();
        }
    }

    public void step() {

        if (state == ElevatorState.IDLE) return;

        Integer next = strategy.getNextStop(this);

        if (next == null) {
            state = ElevatorState.IDLE;
            direction = ElevatorDirection.IDLE;
            notifyStateChange();
            return;
        }

        if (next > floor) floor++;
        else if (next < floor) floor--;

        notifyFloorChange();

        if (floor == next) stop();
    }

    private void stop() {
        state = ElevatorState.STOPPED;
        notifyStateChange();

        requests.remove(floor);

        if (requests.isEmpty()) {
            state = ElevatorState.IDLE;
            direction = ElevatorDirection.IDLE;
        } else {
            state = ElevatorState.MOVING;
        }

        notifyStateChange();
    }
    
    public void printStatus() {
        System.out.println(
            "[Elevator " + id + "] Floor: " + floor +
            " | Dir: " + direction +
            " | State: " + state +
            " | Requests: " + requests
        );
    }
}
```

```java
// Controller

import org.example.lld.Elevator.model.Elevator;

import java.util.*;

class ElevatorController {

    private List<Elevator> elevators;

    public ElevatorController(List<Elevator> elevators) {
        this.elevators = elevators;
    }

    private Elevator findBestElevator(int floor, ElevatorDirection dir) {

        Elevator best = null;
        int minCost = Integer.MAX_VALUE;

        for (Elevator e : elevators) {

            int cost = Math.abs(e.getFloor() - floor);

            if (e.getDirection() == dir) {
                if ((dir == ElevatorDirection.UP && floor >= e.getFloor()) ||
                        (dir == ElevatorDirection.DOWN && floor <= e.getFloor())) {
                    cost -= 5;
                }
            }

            if (e.getDirection() == ElevatorDirection.IDLE) {
                cost -= 2;
            }

            if (cost < minCost) {
                minCost = cost;
                best = e;
            }
        }

        return best;
    }

    public void requestElevator(int floor, ElevatorDirection dir) {
        Elevator best = findBestElevator(floor, dir);
        System.out.println("Assigned Elevator: " + best.getId());
        best.addRequest(floor);
    }

    public void requestFloor(int id, int floor) {
        for (Elevator elevator: elevators) {
            if(elevator.getId() == id) {
                elevator.addRequest(floor);
                break;
            }
        }
    }

    public void step() {
        for (Elevator e : elevators) {
            e.step();
            e.printStatus(); // 🔥 visualization
        }
        System.out.println("--------------------------------");
    }
}
```

```java
// Building
import java.util.*;

class Building {

    private ElevatorController controller;

    public Building(int n, SchedulingStrategy strategy) {

        DisplayPanel display = new DisplayPanel();
        List<Elevator> elevators = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            Elevator e = new Elevator(i, strategy);
            e.addObserver(display);
            elevators.add(e);
        }

        controller = new ElevatorController(elevators);
    }

    public ElevatorController getElevatorController() {
        return controller;
    }
}
```

```java
// Driver
public class ElevatorApplication {

    static void main(String[] args) {

        Building building = new Building(3, new LookScheduling());
        ElevatorController controller = building.getElevatorController();

        System.out.println("=== Initial Requests ===");

        controller.requestElevator(5, ElevatorDirection.UP);
        controller.requestElevator(2, ElevatorDirection.UP);
        controller.requestElevator(-2, ElevatorDirection.DOWN);

        for (int i = 0; i < 15; i++) {
            System.out.println("---------------------------------- TIME " + i + " ----------------------------------");
            if (i == 3) {
                System.out.println(">>> Internal Request: Elevator 1 → 10");
                controller.requestFloor(1, 10);
            }

            if (i == 5) {
                System.out.println(">>> New External Request: Floor 9 DOWN");
                controller.requestElevator(9, ElevatorDirection.DOWN);
            }

            controller.step();

            try {
                Thread.sleep(300);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
```
