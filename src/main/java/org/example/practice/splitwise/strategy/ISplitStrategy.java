package org.example.practice.splitwise.strategy;

import org.example.practice.splitwise.model.Split;
import org.example.practice.splitwise.model.User;

import java.util.List;

public interface ISplitStrategy {
    List<Split> calculate(Double amount, List<User> participants, List<Double> exactValues);
}
