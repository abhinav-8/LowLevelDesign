package org.example.practice.splitwise.driver;

import org.example.practice.splitwise.Command.CommandLineManager;
import org.example.practice.splitwise.service.SplitWiseService;

import java.util.Scanner;

public class SplitwiseApplication {
    public static void main(String[] args) {
        SplitWiseService splitWiseService = new SplitWiseService();
        CommandLineManager commandLineManager = new CommandLineManager(splitWiseService);
        splitWiseService.addUser("Abhinav", "Abhinav");
        splitWiseService.addUser("Sounak", "Sounak");
        splitWiseService.addUser("Abhishek", "Abhishek");
        splitWiseService.addUser("Kartike", "Kartike");

        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if(input.equalsIgnoreCase("exit")) {
                break;
            }
            commandLineManager.execute(input);
        }
    }
}
