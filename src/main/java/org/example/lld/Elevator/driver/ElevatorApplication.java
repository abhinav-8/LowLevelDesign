package org.example.lld.Elevator.driver;

import org.example.lld.Elevator.command.ExternalCommand;
import org.example.lld.Elevator.command.ICommand;
import org.example.lld.Elevator.command.InternalCommand;
import org.example.lld.Elevator.model.Building;
import org.example.lld.Elevator.model.ElevatorDirection;
import org.example.lld.Elevator.service.ElevatorController;
import org.example.lld.Elevator.strategy.LookScheduling;

public class ElevatorApplication {
    static void main(String[] args) {

        Building building = new Building(3, new LookScheduling());
        ElevatorController controller = building.getElevatorController();

        System.out.println("=== Initial External Requests ===");
        ICommand c2 = new ExternalCommand(2, ElevatorDirection.UP, controller);
        ICommand c1 = new ExternalCommand(5, ElevatorDirection.UP, controller);
        ICommand c3 = new ExternalCommand(-2, ElevatorDirection.DOWN, controller);

        c1.execute();
        c2.execute();
        c3.execute();

        for (int t = 0; t < 15; t++) {
            System.out.println("----------------------------- STEP " + t + " -------------------------------------");
            // Phase 1: internal request
            if (t == 3) {
                System.out.println("\n>>> Internal Request: Elevator 1 → Floor 10");
                ICommand c = new InternalCommand(5, 1, controller);
                c.execute();
            }

            // Phase 2: new external request
            if (t == 6) {
                System.out.println("\n>>> New External Request: Floor 9 DOWN");
                ICommand c = new ExternalCommand(9, ElevatorDirection.DOWN, controller);
                c.execute();
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
