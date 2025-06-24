package TicTacToe.strategy;

import TicTacToe.model.Board;
import TicTacToe.model.Move;

public interface WinningStrategy {
    public Boolean isWinning(Board board, Move move);
}
