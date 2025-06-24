package TicTacToe.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Integer boardSize;
    private List<List<Cell>> board;

    public Board(Integer boardSize) {
        this.boardSize = boardSize;

        this.board = new ArrayList<List<Cell>>();

        for(int i = 0; i < this.boardSize; i++) {
            List<Cell> row = new ArrayList<Cell>();
            for(int j = 0; j < this.boardSize; j++) {
                row.add(new Cell(i, j));
            }
            this.board.add(row);
        }

    }

    public Cell getCell(int row, int col) {
        return this.board.get(row).get(col);
    }

    public Integer getBoardSize() {
        return this.boardSize;
    }

    public void printBoard() {
        for(int i = 0; i < this.boardSize; i++) {
            for(int j = 0; j < this.boardSize; j++) {
                System.out.print(this.board.get(i).get(j).isCellEmpty() ? "_ " : this.board.get(i).get(j).getPlayer().getSymbol() + " ");
            }
            System.out.println();
        }
    }
}
