package TicTacToe.service;

import TicTacToe.model.Board;
import TicTacToe.model.Cell;
import TicTacToe.model.Move;
import TicTacToe.model.Player;
import TicTacToe.strategy.WinningStrategy;

import java.util.List;

public class Game {
    private Board board;
    private List<Player> playerList;
    private WinningStrategy winningStrategy;
    private int currentPlayerIndex = 0;
    private Boolean isGameOver = false;
    private int totalMoves = 0;

    public Game(Board board, List<Player> playerList, WinningStrategy winningStrategy) {
        this.board = board;
        this.playerList = playerList;
        this.winningStrategy = winningStrategy;
    }

    public void makeMove(int row, int col) {
        if(isGameOver) {
            System.out.println("Game over");
            return;
        }

        Player currentPlayer = playerList.get(this.currentPlayerIndex);
        Cell cell = board.getCell(row, col);

        if(!cell.isCellEmpty()) {
            System.out.println("Invalid move: Cell is not empty");
            return;
        }

        cell.setPlayer(currentPlayer);
        board.printBoard();
        totalMoves++;

        if(winningStrategy.isWinning(board, new Move(cell, currentPlayer))) {
            isGameOver = true;
            System.out.println("Player " + currentPlayer.getName() + " aka " + currentPlayer.getSymbol() + " won");
            return;
        }

        if(totalMoves == board.getBoardSize() * board.getBoardSize()) {
            isGameOver = true;
            System.out.println("Game over and ended in a draw!");
            return;
        }

        currentPlayerIndex = (currentPlayerIndex + 1) % playerList.size() ;
    }
}
