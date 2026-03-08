package org.example.lld.SnakesAndLadders.service;

import lombok.AllArgsConstructor;
import org.example.lld.SnakesAndLadders.model.Board;
import org.example.lld.SnakesAndLadders.model.Player;
import org.example.lld.SnakesAndLadders.strategy.dice.Dice;
import org.example.lld.SnakesAndLadders.strategy.turn.INextTurnStrategy;
import org.example.lld.SnakesAndLadders.strategy.winning.IWinningStrategy;

import java.util.List;

@AllArgsConstructor
public class Game {
    Board board;
    List<Player> players;
    Dice dice;
    int currentPlayerIndex;
    IWinningStrategy winningStrategy;
    INextTurnStrategy turn;

    public void startGame() {
        while (true) {
            int moves = dice.roll();
            Player currentPlayer = players.get(currentPlayerIndex);
            board.applyEntities(moves, currentPlayer);
            System.out.println("Player: " + currentPlayer.getName() + " moved to: " + currentPlayer.getPosition());

            if (winningStrategy.didPlayerWin(currentPlayer, board)) {
                System.out.println("Player: " + currentPlayer.getName() + " is winner!");
                return;
            }

            currentPlayerIndex = turn.nextTurn(currentPlayerIndex, players.size());
        }
    }
}
