package org.example.practice.splitwise.Command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.practice.splitwise.model.ExpenseSplitType;
import org.example.practice.splitwise.service.SplitWiseService;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ExpenseCommand implements ICommand {
    SplitWiseService splitWiseService;

    @Override
    public void execute(String[] command) {
        try {
            List<String> participantIds = new ArrayList<>();
            String payerId = command[1];
            Double amount = Double.parseDouble(command[2]);
            int n = Integer.parseInt(command[3]);
            int i = 4;
            while (i < 4 + n) {
                String participantId = command[i++];
                participantIds.add(participantId);
            }
            ExpenseSplitType splitType = ExpenseSplitType.valueOf(command[i++]);
            List<Double> values = new ArrayList<>();
            while (i < command.length) {
                values.add(Double.parseDouble(command[i++]));
            }

            splitWiseService.addExpense(payerId, amount, participantIds, splitType, values);
        } catch(Exception e) {
            throw new RuntimeException("Siiu");
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