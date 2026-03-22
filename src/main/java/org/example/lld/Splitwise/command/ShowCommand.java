package org.example.lld.Splitwise.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.lld.Splitwise.service.SplitWiseService;

@Setter
@Getter
@AllArgsConstructor
public class ShowCommand implements Command {
    private SplitWiseService service;
    private String[] inputParts;

//    SHOW
//    SHOW user1
    @Override
    public void execute() {
        if(inputParts.length == 1) {
            service.showAll();
        }
        else{
            service.showUser(inputParts[1]);
        }
    }
}
