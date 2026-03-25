package org.example.lld.Elevator.driver;

import org.example.lld.Elevator.model.Building;
import org.example.lld.Elevator.model.ElevatorDirection;
import org.example.lld.Elevator.service.ElevatorController;
import org.example.lld.Elevator.strategy.LookScheduling;

public class ElevatorApplication {
    static void main(String[] args) {

        Building building = new Building(3, new LookScheduling());
        ElevatorController controller = building.getElevatorController();

        System.out.println("=== Initial External Requests ===");
        controller.requestElevator(5, ElevatorDirection.UP);
        controller.requestElevator(2, ElevatorDirection.UP);
        controller.requestElevator(-2, ElevatorDirection.DOWN);

        for (int t = 0; t < 15; t++) {
            System.out.println("----------------------------- STEP " + t + " -------------------------------------");
            // Phase 1: internal request
            if (t == 3) {
                System.out.println("\n>>> Internal Request: Elevator 1 → Floor 10");
                controller.requestFloor(1, 10);
            }

            // Phase 2: new external request
            if (t == 6) {
                System.out.println("\n>>> New External Request: Floor 9 DOWN");
                controller.requestElevator(9, ElevatorDirection.DOWN);
            }

            // Step simulation
            controller.step();

            // Optional small separator (helps readability)
            System.out.println("--------------------------------");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
//            System.out.println("----------------------------- STEP " + t + " OVER-------------------------------------");
        }
    }
}
