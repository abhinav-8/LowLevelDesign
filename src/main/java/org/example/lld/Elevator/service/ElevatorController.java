package org.example.lld.Elevator.service;

import lombok.AllArgsConstructor;
import org.example.lld.Elevator.model.Elevator;
import org.example.lld.Elevator.model.ElevatorDirection;

import java.util.List;

@AllArgsConstructor
public class ElevatorController {
    private List<Elevator> elevators;

    private Elevator findBestElevator(int floor, ElevatorDirection direction) {
        Elevator bestElevator = null;
        int minCost = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int cost = Math.abs(elevator.getFloor() - floor);

            if(elevator.getDirection() == direction) {
                if((elevator.getDirection() == ElevatorDirection.UP && floor >= elevator.getFloor()) ||
                        (elevator.getDirection() == ElevatorDirection.DOWN && floor <= elevator.getFloor())) {
                    cost -= 5;
                }
            }

            else if(elevator.getDirection() == ElevatorDirection.IDLE) cost -= 2;

            if(cost < minCost){
                minCost = cost;
                bestElevator = elevator;
            }
        }

        return bestElevator;
    }

    //Externally call elevator to ur floor
    public void requestElevator(int floor, ElevatorDirection direction) {
        Elevator best = findBestElevator(floor, direction);
        System.out.println("Assigned best elevator: " + best.getId());
        best.addRequest(floor);
    }

    //Internally in lift go to a floor
    public void requestFloor(int elevatorId, int floor) {
        for (Elevator elevator : elevators) {
            if (elevator.getId() == elevatorId) {
                elevator.addRequest(floor);
                break;
            }
        }
    }

    public void step() {
        for (Elevator e : elevators) {
            e.step();
            e.printStatus();
            System.out.println("------------------------------------------------");
        }
    }
}
