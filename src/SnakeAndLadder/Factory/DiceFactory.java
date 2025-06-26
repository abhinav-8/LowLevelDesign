package SnakeAndLadder.Factory;

import SnakeAndLadder.model.Dice.CrookedDice;
import SnakeAndLadder.model.Dice.Dice;
import SnakeAndLadder.model.Dice.NormalDice;

public class DiceFactory {
    public static Dice getDice (String type){
        if(type.equalsIgnoreCase("NormalDice")){
            return new NormalDice();
        }
        if(type.equalsIgnoreCase("CrookedDice")){
            return new CrookedDice();
        }
        return null;
    }
}
