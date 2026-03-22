package org.example.lld.Splitwise.driver;

import org.example.lld.Splitwise.command.CommandLineManager;
import org.example.lld.Splitwise.service.SplitWiseService;

import java.util.Scanner;

public class SplitWiseApplication {
    public static void main(String[] args) {
        SplitWiseService service = new SplitWiseService();
        service.addUser("1", "Abhinav");
        service.addUser("2", "Abhishek");
        service.addUser("3", "Kartike");
        service.addUser("4", "Sounak");

        CommandLineManager manager = new CommandLineManager(service);
        Scanner sc = new Scanner(System.in);

        while(true){

            String input = sc.nextLine();

            // optional exit condition
            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("Closing application...");
                break;
            }

            manager.execute(input);
        }

        sc.close();
    }
}


