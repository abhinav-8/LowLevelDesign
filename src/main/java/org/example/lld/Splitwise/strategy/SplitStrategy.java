package org.example.lld.Splitwise.strategy;

import org.example.lld.Splitwise.model.Split;
import org.example.lld.Splitwise.model.User;

import java.util.List;

public interface SplitStrategy {
    List<Split> calculate(User payer, Double amount, List<User> participants, List<Double> values);
}
