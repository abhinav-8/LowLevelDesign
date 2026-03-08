package org.example.lld.SnakesAndLadders.strategy.turn;

public class Random implements INextTurnStrategy{
    @Override
    public int nextTurn(int currPlayerIndex, int noOfPlayers) {
        return (int) (Math.random() * noOfPlayers);
    }
}
