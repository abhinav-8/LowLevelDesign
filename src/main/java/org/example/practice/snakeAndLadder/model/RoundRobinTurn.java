package org.example.practice.snakeAndLadder.model;

import org.example.practice.snakeAndLadder.strategy.INextTurnStrategy;

public class RoundRobinTurn implements INextTurnStrategy {
    @Override
    public int nextTurn(int n, int currPlayerInd) {
        return (currPlayerInd+1) % n;
    }
}
