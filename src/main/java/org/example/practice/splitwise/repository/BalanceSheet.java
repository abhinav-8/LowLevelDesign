package org.example.practice.splitwise.repository;

import org.example.practice.splitwise.model.Split;
import org.example.practice.splitwise.model.User;
import org.example.practice.splitwise.strategy.ISplitStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BalanceSheet {
    private final Map<String, Map<String, Double>> balances = new HashMap<>();

    public void addExpense(User payer, Double amount, List<User> participants, List<Double> exactValues, ISplitStrategy splitStrategy) {
        List<Split> splitList = splitStrategy.calculate(amount, participants, exactValues);
        balances.putIfAbsent(payer.getId(), new HashMap<>());
        for(User participant : participants) {
            balances.putIfAbsent(participant.getId(), new HashMap<>());
        }
        for(Split split : splitList) {
            User participant = split.getUser();
            if(participant.getId().equals(payer.getId())) {
                continue;
            }
            balances.get(payer.getId()).put(participant.getId(), balances.get(payer.getId()).getOrDefault(participant.getId(), 0.0) - split.getShare());
            balances.get(participant.getId()).put(payer.getId(), balances.get(participant.getId()).getOrDefault(payer.getId(), 0.0) + split.getShare());
        }
    }

    public void showAll() {
        for(String user1 : balances.keySet()) {
            for(String user2 : balances.get(user1).keySet()) {
                Double share = balances.get(user1).get(user2);
                if(share > 0) {
                    System.out.println(user1 + " owes " + user2 + ": " + share);
                }
            }
        }
    }

    public void showForUserId(String userId) {
        for(String user2 : balances.get(userId).keySet()) {
                Double share = balances.get(userId).get(user2);
                if(share > 0) {
                    System.out.println(userId + " owes " + user2 + ": " + share);
                }
                else if(share < 0) {
                    System.out.println(user2 + " owes " + userId + ": " + Math.abs(share));
                }
        }
    }
}
