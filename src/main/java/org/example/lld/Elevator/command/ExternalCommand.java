package org.example.lld.Elevator.command;

import lombok.AllArgsConstructor;
import org.example.lld.Elevator.model.ElevatorDirection;
import org.example.lld.Elevator.service.ElevatorController;

@AllArgsConstructor
public class ExternalCommand implements ICommand {
    int floor;
    ElevatorDirection direction;
    ElevatorController controller;

    @Override
    public void execute() {
        controller.requestElevator(floor, direction);
    }
}
