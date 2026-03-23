package org.example.practice.splitwise.strategy;

import org.example.practice.splitwise.model.Split;
import org.example.practice.splitwise.model.User;

import java.util.ArrayList;
import java.util.List;

public class EqualSplitStrategy implements ISplitStrategy {
    @Override
    public List<Split> calculate(Double amount, List<User> participants, List<Double> exactValues) {
        int n = participants.size();
        List<Split> splits = new ArrayList<>();
        double share = Math.round((amount / n) * 100.0) / 100.0;
        for (User participant : participants) {
            splits.add(new Split(participant, share));
        }
        return splits;
    }
}


