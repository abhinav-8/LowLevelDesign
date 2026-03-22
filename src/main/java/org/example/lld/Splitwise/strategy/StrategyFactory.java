package org.example.lld.Splitwise.strategy;

import org.example.lld.Splitwise.model.ExpenseSplitType;

import static org.example.lld.Splitwise.model.ExpenseSplitType.EQUAL;
import static org.example.lld.Splitwise.model.ExpenseSplitType.EXACT;

public class StrategyFactory {
    public SplitStrategy getStrategy(ExpenseSplitType splitType) {
        if (splitType == EQUAL) return new EqualSplitStrategy();
        else if (splitType == EXACT) return new ExactSplitStrategy();
        throw new RuntimeException("Unsupported split type!");
    }
}
