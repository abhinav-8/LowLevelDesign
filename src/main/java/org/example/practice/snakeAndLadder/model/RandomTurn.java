package org.example.practice.snakeAndLadder.model;

import org.example.practice.snakeAndLadder.strategy.INextTurnStrategy;

public class RandomTurn implements INextTurnStrategy {

    @Override
    public int nextTurn(int n, int currPlayerInd) {
        return (int)(Math.random() * n);
    }
}