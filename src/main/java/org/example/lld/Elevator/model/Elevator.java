package org.example.lld.Elevator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.lld.Elevator.observer.ElevatorObserver;
import org.example.lld.Elevator.strategy.SchedulingStrategy;
import org.example.lld.ParkingLot.model.ParkingLot;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Getter
@Setter
public class Elevator {
    private int id;
    private int floor = 0;
    private ElevatorState state;
    private ElevatorDirection direction;

    private SchedulingStrategy schedulingStrategy;
    private TreeSet<Integer> requests;
    private List<ElevatorObserver> observers;

    public Elevator(int id, SchedulingStrategy schedulingStrategy) {
        this.id = id;
        this.floor = 0;
        this.state = ElevatorState.IDLE;
        this.direction = ElevatorDirection.IDLE;
        this.schedulingStrategy = schedulingStrategy;
        this.requests = new TreeSet<>();
        this.observers = new ArrayList<>();
    }

    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
    }

    public void notifyFloorChange() {
        for (ElevatorObserver observer : observers) {
            observer.onFloorChange(id, floor);
        }
    }

    public void notifyStateChange() {
        for (ElevatorObserver observer : observers) {
            observer.onStateChange(id, state);
        }
    }
    public void addRequest(int floor) {
        requests.add(floor);
        if(state == ElevatorState.IDLE){
            state = ElevatorState.MOVING;
            direction = (floor > this.floor) ? ElevatorDirection.UP : ElevatorDirection.DOWN;
            notifyStateChange();
        }
    }

    public void step() {
        if(state == ElevatorState.IDLE) return;

        Integer nextFloor = schedulingStrategy.getNextStop(this);

        if(nextFloor == null) {
            state = ElevatorState.IDLE;
            direction = ElevatorDirection.IDLE;
            notifyStateChange();
            return;
        }

        if(nextFloor > floor) floor++;
        else if(nextFloor < floor) floor--;

        notifyFloorChange();
        if(nextFloor == floor) {
            stop();
        }
    }

    public void stop() {
        state = ElevatorState.STOPPED;
        notifyStateChange();

        requests.remove(floor);

        if(requests.isEmpty()) {
            state = ElevatorState.IDLE;
            direction = ElevatorDirection.IDLE;
            notifyStateChange();
        } else {
            state = ElevatorState.MOVING;
        }
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

