package org.example.practice.splitwise.factory;

import org.example.practice.splitwise.model.ExpenseSplitType;
import org.example.practice.splitwise.strategy.EqualSplitStrategy;
import org.example.practice.splitwise.strategy.ExactSplitStrategy;
import org.example.practice.splitwise.strategy.ISplitStrategy;

public class SplitFactory {
    public static ISplitStrategy getSplitStrategy(ExpenseSplitType splitType) {
        if(splitType == ExpenseSplitType.EQUAL) {
            return new EqualSplitStrategy();
        }
        if(splitType == ExpenseSplitType.EXACT) {
            return new ExactSplitStrategy();
        }
        throw new IllegalArgumentException("Invalid splitType");
    }
}
