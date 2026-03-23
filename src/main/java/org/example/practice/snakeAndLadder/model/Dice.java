package org.example.practice.snakeAndLadder.model;

import lombok.AllArgsConstructor;
import org.example.practice.snakeAndLadder.strategy.DiceRollStrategy;

@AllArgsConstructor
public class Dice implements DiceRollStrategy {

    int faces;

    public Dice() {
        this.faces = 6;
    }

    @Override
    public int roll() {
        return (int)(Math.random() * (faces+1));
    }
}
