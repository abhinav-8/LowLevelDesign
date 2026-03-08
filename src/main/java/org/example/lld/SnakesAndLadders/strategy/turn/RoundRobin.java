package org.example.lld.SnakesAndLadders.strategy.turn;

public class RoundRobin implements INextTurnStrategy{
    @Override
    public int nextTurn(int currPlayerIndex, int noOfPlayers) {
        return (currPlayerIndex + 1) % noOfPlayers;
    }
}
