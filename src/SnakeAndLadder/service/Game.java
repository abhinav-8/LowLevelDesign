package SnakeAndLadder.service;


import SnakeAndLadder.model.Dice.Dice;
import SnakeAndLadder.model.Ladder;
import SnakeAndLadder.model.Player;
import SnakeAndLadder.model.Snake;

import java.util.List;
import java.util.Scanner;

public class Game {
    private final int MAX_POSITION = 100;
    private final List<Snake> snakes;
    private final List<Ladder> ladders;
    private final List<Player> players;
    private final Dice dice;
    private int currPlayer = 0;
    private Boolean isGameOver = false;

    public Game(List<Snake> snakes, List<Ladder> ladders, List<Player> players, Dice dice) {
        this.snakes = snakes;
        this.ladders = ladders;
        this.players = players;
        this.dice = dice;
    }

    public void makeMove() {
        int nextPos = players.get(currPlayer).getPos() + dice.roll();

        if (nextPos > MAX_POSITION) {
            System.out.println("Dice roll out of bounds!");
            currPlayer = (currPlayer + 1) % players.size();
            return;
        }

        int newPos = isClimbLadder(nextPos);
        newPos = isBittenBySnake(newPos);

        if (newPos == MAX_POSITION) {
            players.get(currPlayer).setPos(nextPos);
            System.out.println(players.get(currPlayer).getName() + " won!");
            isGameOver = true;
            return;
        }
        players.get(currPlayer).setPos(newPos);
        System.out.println(players.get(currPlayer).getName() + " is at " + newPos);
        currPlayer = (currPlayer + 1) % players.size();
    }

    private int isBittenBySnake(int nextPos) {
        for(Snake snake : snakes) {
            if(nextPos == snake.getStart()){
                System.out.println("Snake found!");
                return snake.getEnd();
            }
        }
        return nextPos;
    }

    private int isClimbLadder(int nextPos) {
        for(Ladder ladder : ladders) {
            if(nextPos == ladder.getStart()){
                System.out.println("Ladder found!");
                return ladder.getEnd();
            }
        }
        return nextPos;
    }

    public void play() {
        System.out.println("Welcome to Snake and Ladder!");
        System.out.println("To roll the dice and make a move type 'play' or press 'p' ");
        System.out.println("Type 'exit' to exit the game");
        Scanner scanner = new Scanner(System.in);
        while(!isGameOver) {
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Game ended without a result!");
                isGameOver = true;
                break;
            }
            if (command.equalsIgnoreCase("play") || command.equalsIgnoreCase("p")) {
                makeMove();
            }
            else {
                System.out.println("That is not a valid command!");
                isGameOver = true;
                break;
            }
        }
    }

}
