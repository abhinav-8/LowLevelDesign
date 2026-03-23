package org.example.practice.splitwise.Command;

import lombok.AllArgsConstructor;
import org.example.practice.splitwise.service.SplitWiseService;

import java.util.Objects;


@AllArgsConstructor
public class CommandLineManager {
    SplitWiseService splitWiseService;

    public void execute(String input) {
        String[] inputs = input.split(" ");
        ICommand command;
        if(Objects.equals(inputs[0], "EXPENSE")) {
            command = new ExpenseCommand(splitWiseService);
        } else {
            command = new ShowCommand(splitWiseService);
        }
        command.execute(inputs);
    }
}
