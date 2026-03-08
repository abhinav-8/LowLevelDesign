package org.example.lld.SnakesAndLadders.strategy.winning;

import org.example.lld.SnakesAndLadders.model.Board;
import org.example.lld.SnakesAndLadders.model.Player;

public class ReachEndAnd10CoinsWinningStrategy implements IWinningStrategy {
    @Override
    public boolean didPlayerWin(Player player, Board board) {
        return player.getPosition() == board.getSize() || player.getRewards() == 10;
    }
}
