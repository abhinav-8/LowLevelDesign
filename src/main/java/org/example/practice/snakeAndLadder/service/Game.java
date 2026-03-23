package org.example.practice.snakeAndLadder.service;

import lombok.AllArgsConstructor;
import org.example.practice.snakeAndLadder.strategy.IWinningStrategy;
import org.example.practice.snakeAndLadder.model.Board;
import org.example.practice.snakeAndLadder.model.User;
import org.example.practice.snakeAndLadder.strategy.DiceRollStrategy;
import org.example.practice.snakeAndLadder.strategy.INextTurnStrategy;

import java.util.List;

@AllArgsConstructor
public class Game {
    private List<User> players;
    private Board board;
    private DiceRollStrategy rollStrategy;
    private INextTurnStrategy nextTurnStrategy;
    private IWinningStrategy winningStrategy;
    int currPlayerIndex = 0;

    public void playGame() {
        while (true) {
            int roll = rollStrategy.roll();
            board.movePlayer(players.get(currPlayerIndex), roll);
            if(winningStrategy.isWinner(players.get(currPlayerIndex), board)) {
                System.out.println(players.get(currPlayerIndex).getName() + " wins!");
                break;
            }
            currPlayerIndex = nextTurnStrategy.nextTurn(players.size(), currPlayerIndex);
        }
    }
}
