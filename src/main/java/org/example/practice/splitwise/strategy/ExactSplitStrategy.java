package org.example.practice.splitwise.strategy;

import org.example.practice.splitwise.model.Split;
import org.example.practice.splitwise.model.User;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class ExactSplitStrategy implements ISplitStrategy {
    @Override
    public List<Split> calculate(Double amount, List<User> participants, List<Double> exactValues) {
        int n = participants.size();
        int sum = 0;
        List<Split> splits = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sum += exactValues.get(i);
            splits.add(new Split(participants.get(i), exactValues.get(i)));
        }
        if(abs(sum-amount) > 0.01) {
            throw new RuntimeException("Amount and sum of shares should be equal!");
        }
        return splits;
    }
}
