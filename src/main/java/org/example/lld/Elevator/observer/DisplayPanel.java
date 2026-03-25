package org.example.lld.Elevator.observer;

import org.example.lld.Elevator.model.ElevatorState;

public class DisplayPanel implements ElevatorObserver {

    @Override
    public void onFloorChange(int id, int floor) {
        System.out.println("[Elevator " + id + "] reached floor: " + floor);
    }

    @Override
    public void onStateChange(int id, ElevatorState state) {
        System.out.println("[Elevator " + id + "] state changed to: " + state);
    }
}
