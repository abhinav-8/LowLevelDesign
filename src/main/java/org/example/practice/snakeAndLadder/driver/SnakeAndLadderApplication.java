package org.example.practice.snakeAndLadder.driver;

import org.example.practice.snakeAndLadder.service.Game;
import org.example.practice.snakeAndLadder.model.Dice;
import org.example.practice.snakeAndLadder.entity.Ladder;
import org.example.practice.snakeAndLadder.entity.Snake;
import org.example.practice.snakeAndLadder.model.Board;
import org.example.practice.snakeAndLadder.model.ReachEnd;
import org.example.practice.snakeAndLadder.model.RoundRobinTurn;
import org.example.practice.snakeAndLadder.model.User;

import java.util.Arrays;

public class SnakeAndLadderApplication {
    static void main(String[] args) {
        User player1 = new User("1", "Abhinav", "Yellow",0);
        User player2 = new User("2", "Avinash", "Red",0);
        Board board = new Board(100, Arrays.asList(new Ladder(10,88, "Ladder"), new Ladder(8,42, "Ladder"), new Ladder(12,36, "Ladder"), new Snake(28, 6, "Snake"), new Snake(56, 22, "Snake"), new Snake(75,20, "Snake")));
        ReachEnd reachEnd = new ReachEnd();
        RoundRobinTurn roundRobinTurn = new RoundRobinTurn();
        Game game = new Game(Arrays.asList(player1, player2), board, new Dice(), roundRobinTurn, reachEnd, 0);
        game.playGame();

    }
}
