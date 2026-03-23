package org.example.practice.snakeAndLadder.strategy;

import org.example.practice.snakeAndLadder.model.Board;
import org.example.practice.snakeAndLadder.model.User;

public interface IWinningStrategy {
    boolean isWinner(User player, Board board);
}
