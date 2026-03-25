package org.example.lld.Elevator.observer;

import org.example.lld.Elevator.model.ElevatorState;

public interface ElevatorObserver {
    void onFloorChange(int eId, int floor);
    void onStateChange(int eId, ElevatorState state);
}
