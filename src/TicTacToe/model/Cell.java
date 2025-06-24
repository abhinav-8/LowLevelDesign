package TicTacToe.model;

public class Cell {
    private int row;
    private int col;
    private Player player;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Boolean isCellEmpty() {
        return this.player == null;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public Player getPlayer() {
        return player;
    }
}
