package org.example.lld.Splitwise.service;

import com.jetbrains.exported.JBRApi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.lld.Splitwise.model.Expense;
import org.example.lld.Splitwise.model.ExpenseSplitType;
import org.example.lld.Splitwise.model.User;
import org.example.lld.Splitwise.repository.BalanceSheet;
import org.example.lld.Splitwise.strategy.SplitStrategy;
import org.example.lld.Splitwise.strategy.StrategyFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SplitWiseService {
    StrategyFactory strategyFactory = new StrategyFactory();
    BalanceSheet balanceSheet = new BalanceSheet();
    Map<String, User> userMap = new HashMap<>();

    public void addUser(String id, String name) {
        User user = new User(id, name, "", "");
        userMap.put(id, user);
        balanceSheet.addObserver(user);
    }

    public void addExpense(String payerId, Double amount, ExpenseSplitType splitType, List<String> participantIds, List<Double> expenseVals, String description) {
        SplitStrategy strategy = strategyFactory.getStrategy(splitType);
        List<User> participants = new ArrayList<>();
        for (String id : participantIds) {
            participants.add(userMap.get(id));
        }
        Expense expense = new Expense(amount, userMap.get(payerId), strategy.calculate(userMap.get(payerId), amount, participants, expenseVals), description);
        balanceSheet.addExpense(expense);
    }

    public void showAll() {
        balanceSheet.showBalances();
    }

    public void showUser(String userId) {
        balanceSheet.showBalancesForUser(userId);
    }
}
