package org.example.lld.Elevator.strategy;

import org.example.lld.Elevator.model.Elevator;
import org.example.lld.Elevator.model.ElevatorDirection;

public class LookScheduling implements SchedulingStrategy {

    @Override
    public Integer getNextStop(Elevator elevator) {
        if(elevator.getRequests().isEmpty()) return null;

        int currentFloor = elevator.getFloor();

        Integer up = elevator.getRequests().higher(currentFloor);
        Integer down = elevator.getRequests().lower(currentFloor);

        if(elevator.getDirection() == ElevatorDirection.UP) {
            if(up != null) return up;

            elevator.setDirection(ElevatorDirection.DOWN);
            return down;
        }

        else if(elevator.getDirection() == ElevatorDirection.DOWN) {
            if(down != null) return down;

            elevator.setDirection(ElevatorDirection.UP);
            return up;
        }

        else {
            if(up == null)  {
                elevator.setDirection(ElevatorDirection.DOWN);
                return down;
            }

            if(down == null)  {
                elevator.setDirection(ElevatorDirection.UP);
                return up;
            }

            if(Math.abs(up - currentFloor) < Math.abs(down - currentFloor)) {
                elevator.setDirection(ElevatorDirection.UP);
                return up;
            }

            elevator.setDirection(ElevatorDirection.DOWN);
            return down;

        }
    }
}
