package TicTacToe.service;

import TicTacToe.model.Board;
import TicTacToe.model.Cell;
import TicTacToe.model.Move;
import TicTacToe.model.Player;
import TicTacToe.strategy.WinningStrategy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;


public class Game {
    private final Board board;
    private final List<Player> playerList;
    private final WinningStrategy winningStrategy;
    private Deque<Move> moveHistory = new ArrayDeque<>();
    private int currentPlayerIndex = 0;
    private Boolean isGameOver = false;
    private int totalMoves = 0;

    public Game(GameBuilder gameBuilder) {
        this.board = gameBuilder.board;
        this.playerList = gameBuilder.playerList;
        this.winningStrategy = gameBuilder.winningStrategy;
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
        Move move = new Move(cell, currentPlayer);
        moveHistory.push(move);
        currentPlayerIndex = (currentPlayerIndex + 1) % playerList.size() ;
        if(winningStrategy.isWinning(board, move)) {
            isGameOver = true;
            System.out.println("Player " + currentPlayer.getName() + " aka " + currentPlayer.getSymbol() + " won");
            return;
        }

        if(totalMoves == board.getBoardSize() * board.getBoardSize()) {
            isGameOver = true;
            System.out.println("Game over and ended in a draw!");
            return;
        }

    }

    public void undo() {
        if(moveHistory.isEmpty()) {
            System.out.println("No more moves to undo");
            return;
        }
        Move lastMove = moveHistory.pop();
        Cell cell = lastMove.getCell();
        cell.setPlayer(null);
        currentPlayerIndex = (currentPlayerIndex - 1 + playerList.size()) % playerList.size() ;
        isGameOver = false;
        totalMoves--;
        board.printBoard();
    }

    public static class GameBuilder {
        private Board board;
        private List<Player> playerList;
        private WinningStrategy winningStrategy;

        public GameBuilder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public GameBuilder setPlayerList(List<Player> playerList) {
            this.playerList = playerList;
            return this;
        }
        public GameBuilder setWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }
        public Game build() {
            if (this.board == null) {
                throw new IllegalArgumentException("Board cannot be null");
            }
            if (this.playerList == null || this.playerList.isEmpty()) {
                throw new IllegalArgumentException("Player list cannot be null or empty");
            }
            if (this.winningStrategy == null) {
                throw new IllegalArgumentException("WinningStrategy cannot be null");
            }
            return new Game(this);
        }
    }
}
