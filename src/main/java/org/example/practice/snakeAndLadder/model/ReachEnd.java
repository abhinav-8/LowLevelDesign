package org.example.practice.snakeAndLadder.model;

import org.example.practice.snakeAndLadder.strategy.IWinningStrategy;

public class ReachEnd implements IWinningStrategy {

    @Override
    public boolean isWinner(User player, Board board) {
        return player.getCurrPos() == board.getSize();
    }
}
