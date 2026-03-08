package org.example.lld.SnakesAndLadders.strategy.winning;

import org.example.lld.SnakesAndLadders.model.Board;
import org.example.lld.SnakesAndLadders.model.Player;

public interface IWinningStrategy {
    boolean didPlayerWin(Player player, Board board);
}
