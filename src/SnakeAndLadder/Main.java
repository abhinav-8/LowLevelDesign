package SnakeAndLadder;


import SnakeAndLadder.Factory.DiceFactory;
import SnakeAndLadder.model.Dice.Dice;
import SnakeAndLadder.model.Ladder;
import SnakeAndLadder.model.Player;
import SnakeAndLadder.model.Snake;
import SnakeAndLadder.service.Game;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Play Snake and Ladder
        List<Snake> snakes = Arrays.asList(new Snake(31,7), new Snake(78,23), new Snake(99,13));
        List<Ladder> ladders = Arrays.asList(new Ladder(10,27), new Ladder(28, 57), new Ladder(5, 16), new Ladder(67, 97));
        List<Player> players = Arrays.asList(new Player("Abhinav", 0, 'A'), new Player("Avinash", 0, 'B'));
        Dice dice = DiceFactory.getDice("normaldice");
        Game game = new Game(snakes, ladders, players, dice);
        game.play();
    }
}
