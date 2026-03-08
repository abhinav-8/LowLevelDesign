package org.example.lld.SnakesAndLadders.driver;

import org.example.lld.SnakesAndLadders.entity.Coin;
import org.example.lld.SnakesAndLadders.entity.IGameEntity;
import org.example.lld.SnakesAndLadders.entity.Ladder;
import org.example.lld.SnakesAndLadders.entity.Snake;
import org.example.lld.SnakesAndLadders.model.Board;
import org.example.lld.SnakesAndLadders.model.Player;
import org.example.lld.SnakesAndLadders.service.Game;
import org.example.lld.SnakesAndLadders.strategy.dice.Dice;
import org.example.lld.SnakesAndLadders.strategy.turn.RoundRobin;
import org.example.lld.SnakesAndLadders.strategy.winning.ReachEndWinningStrategy;

import java.util.Arrays;
import java.util.List;

public class SnakeLadderApplication {
    void main(String[] args) {
        List<IGameEntity> entities = Arrays.asList(new Snake(40, 10), new Snake(92, 48), new Ladder(19, 60), new Ladder(32, 88), new Coin(10, 4), new Coin(5, 8));
        Board board = new Board(100, entities);
        List<Player> players = Arrays.asList(new Player("A", "blue", 0, 0), new Player("B", "red", 0, 0));
        Dice dice = new Dice(6);
        ReachEndWinningStrategy reachEndWinningStrategy = new ReachEndWinningStrategy();
        RoundRobin roundRobin = new RoundRobin();
        Game game = new Game(board, players, dice, 0, reachEndWinningStrategy, roundRobin);
        game.startGame();
    }
}
