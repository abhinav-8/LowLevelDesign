package TicTacToe.strategy;

import TicTacToe.model.Board;
import TicTacToe.model.Move;
import TicTacToe.model.Player;

public class RowColDiagonalWinning implements WinningStrategy{

    @Override
    public Boolean isWinning(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        int size = board.getBoardSize();
        Player player = move.getPlayer();

        return  isRowWinning(board, col, size, player) || isColWinning(board, row, size, player) || isDiagonalWinning(board, row, col, size, player) ;
    }

    private Boolean isRowWinning(Board board, int col, int size, Player player) {
        for (int row = 0; row < size; row++) {
            if(board.getCell(row, col).getPlayer() != player) {
                return false;
            }
        }
        return true;
    }
    private Boolean isColWinning(Board board, int row, int size, Player player) {
        for (int col = 0; col < size; col++) {
            if(board.getCell(row, col).getPlayer() != player) {
                return false;
            }
        }
        return true;
    }
    private Boolean isDiagonalWinning(Board board, int row, int col , int size, Player player) {
        if(row != col && row+col != size - 1) return false;

        if(row == col) {
            for(int i = 0; i < size; i++) {
                if(board.getCell(i, i).getPlayer() != player) {
                    return false;
                }
            }
        }

        if(row + col == size -1) {
            for(int i = 0; i < size; i++) {
                if(board.getCell(i, size - i -1).getPlayer() != player) {
                    return false;
                }
            }
        }

        return  true;
    }
}
