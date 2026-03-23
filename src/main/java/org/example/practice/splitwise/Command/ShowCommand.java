package org.example.practice.splitwise.Command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.practice.splitwise.service.SplitWiseService;

@AllArgsConstructor
@Setter
@Getter
public class ShowCommand implements ICommand {

    SplitWiseService splitWiseService;

    @Override
    public void execute(String[] command) {
       if(command.length == 1) {
           splitWiseService.showAll();
       } else {
           splitWiseService.showUser(command[1]);
       }

    }
}

//
//- EXPENSE 1 1000 4 1 2 3 4 EQUAL
//- SHOW
//- SHOW 1
//- SHOW 2
//- EXPENSE 4 1000 2 1 3 EXACT 750 250
//- SHOW
//- SHOW 1