package org.example.lld.SnakesAndLadders.strategy.dice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Dice implements DiceRollStrategy{
    int faces;

    public Dice(){
        this.faces = 6;
    }

    @Override
    public int roll() {
        return (int) (Math.random()*faces) + 1;
    }
}
