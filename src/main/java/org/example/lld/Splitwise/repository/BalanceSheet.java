package org.example.lld.Splitwise.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.lld.Splitwise.model.Expense;
import org.example.lld.Splitwise.model.Split;
import org.example.lld.Splitwise.observer.Observer;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BalanceSheet {
    Map<String, Map<String, Double>> balances = new HashMap<>();
    List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers(String msg) {
        for (Observer u : observers) {
            u.update(msg);
        }
    }

    public void addExpense(Expense expense) {
        String payerId = expense.getPayer().getId();
        List<Split> splits = expense.getSplits();
        for (Split split : splits) {
            String participantId = split.getParticipant().getId();
            if (Objects.equals(payerId, participantId)) continue;
            balances.putIfAbsent(payerId, new HashMap<>());
            balances.putIfAbsent(participantId, new HashMap<>());

            balances.get(payerId).put(
                    participantId,
                    balances.get(payerId).getOrDefault(participantId, 0.0) - split.getShare()
            );

            balances.get(participantId).put(
                    payerId,
                    balances.get(participantId).getOrDefault(payerId, 0.0) + split.getShare()
            );

            notifyAllObservers(participantId + " owes " + payerId + ": " + split.getShare());
        }
    }

    public void showBalances() {
        for (String u1 : balances.keySet()) {
            for (String u2 : balances.get(u1).keySet()) {
                Double amount = balances.get(u1).get(u2);
                if (amount > 0) {
                    System.out.println(u1 + " owes " + u2 + ": " + amount);
                }
            }
        }
        if(balances.isEmpty()) {
            System.out.println("No balances");
        }
    }

    public void showBalancesForUser(String userId) {

        if (!balances.containsKey(userId)) {
            System.out.println("No balances");
            return;
        }

        for (String u2 : balances.get(userId).keySet()) {
            Double amount = balances.get(userId).get(u2);
            if (amount > 0) {
                System.out.println(userId + " owes " + u2 + ": " + amount);
            } else if (amount < 0) {
                System.out.println(u2 + " owes " + userId + ": " + Math.abs(amount));
            }
        }
    }
}
