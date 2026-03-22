package org.example.lld.Splitwise.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.lld.Splitwise.service.SplitWiseService;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommandLineManager {
    SplitWiseService service;
//    EXPENSE user1 1000 4 user1 user2 user3 user4 EQUAL
//    EXPENSE user1 1250 user2 user3 EXACT 370 880
//    SHOW
//    SHOW user1
    public void execute(String commandInput) {
        final String[] inputParts = commandInput.split(" ");

        //Validate the input format
        String commandName = inputParts[0];
        Command command;
        if(Objects.equals(commandName, "SHOW")){
            command = new ShowCommand(service, inputParts);
        } else {
            command = new ExpenseCommand(service, inputParts);
        }

        command.execute();
    }
}
