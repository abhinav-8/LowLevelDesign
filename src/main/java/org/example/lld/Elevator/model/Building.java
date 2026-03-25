package org.example.lld.Elevator.model;

import lombok.Getter;
import org.example.lld.Elevator.observer.DisplayPanel;
import org.example.lld.Elevator.service.ElevatorController;
import org.example.lld.Elevator.strategy.SchedulingStrategy;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Building {
    private final ElevatorController elevatorController;

    public Building(int n, SchedulingStrategy schedulingStrategy) {
        DisplayPanel displayPanel = new DisplayPanel();
        List<Elevator> elevators = new ArrayList<>();

        for(int i = 1; i <= n; i++){
            Elevator elevator = new Elevator(i, schedulingStrategy);
            elevator.addObserver(displayPanel);
            elevators.add(elevator);
        }

        this.elevatorController = new ElevatorController(elevators);
    }
    
}
