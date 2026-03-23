package org.example.practice.splitwise.service;

import org.example.practice.splitwise.factory.SplitFactory;
import org.example.practice.splitwise.model.ExpenseSplitType;
import org.example.practice.splitwise.model.User;
import org.example.practice.splitwise.repository.BalanceSheet;
import org.example.practice.splitwise.strategy.ISplitStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitWiseService {
    Map<String, User> userMap = new HashMap<>();
    BalanceSheet balanceSheet = new BalanceSheet();

    public void addUser(String id, String name) {
        User user = new User(id, name);
        userMap.put(id, user);
    }

    public void addExpense(String payerId, Double amount, List<String>participantIds, ExpenseSplitType splitType, List<Double> exactValues) {
        User payer = userMap.get(payerId);
        List<User> participantList = new ArrayList<>();
        for (String participantId : participantIds) {
            participantList.add(userMap.get(participantId));
        }
        ISplitStrategy splitStrategy = SplitFactory.getSplitStrategy(splitType);
        balanceSheet.addExpense(payer, amount, participantList, exactValues, splitStrategy);
    }
    public void showAll() {
        balanceSheet.showAll();
    }

    public void showUser(String userId) {
        balanceSheet.showForUserId(userId);
    }
}
