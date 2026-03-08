package org.example.lld.SnakesAndLadders.strategy.turn;

public interface INextTurnStrategy {
    int nextTurn(int currPlayerIndex, int noOfPlayers);
}
