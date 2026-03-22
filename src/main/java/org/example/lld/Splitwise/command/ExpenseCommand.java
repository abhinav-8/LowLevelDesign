package org.example.lld.Splitwise.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.lld.Splitwise.model.ExpenseSplitType;
import org.example.lld.Splitwise.service.SplitWiseService;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ExpenseCommand implements Command {
    private SplitWiseService service;
    private String[] inputParts;
//    EXPENSE user1 1000 4 user1 user2 user3 user4 EQUAL
//    EXPENSE user1 1250 2 user2 user3 EXACT 370 880
    @Override
    public void execute() {
        String payerId = inputParts[1];
        Double amount = Double.parseDouble(inputParts[2]);
        int n = Integer.parseInt(inputParts[3]);
        List<String> participantIds = new ArrayList<>();
        List<Double> expenseVal = new ArrayList<>();
        int i;
        for(i = 4 ; i < 4+n ; i++){
            participantIds.add(inputParts[i]);
        }
        String type = inputParts[i++];
        ExpenseSplitType splitType = ExpenseSplitType.valueOf(type);
        while(i < inputParts.length){
            expenseVal.add(Double.parseDouble(inputParts[i]));
            i++;
        }
        service.addExpense(payerId,amount, splitType, participantIds, expenseVal, "TEST");
    }
}
