package org.example.lld.Elevator.command;

import lombok.AllArgsConstructor;
import org.example.lld.Elevator.service.ElevatorController;

@AllArgsConstructor
public class InternalCommand implements ICommand {
    int floor;
    int elevatorId;
    ElevatorController controller;

    @Override
    public void execute() {
        controller.requestFloor(elevatorId, floor);
    }
}
