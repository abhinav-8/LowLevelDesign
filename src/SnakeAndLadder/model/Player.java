package SnakeAndLadder.model;

public class Player {
    private final String name;
    private final char symbol;
    private int pos;

    public Player(String name, Integer pos, char symbol) {
        this.name = name;
        this.pos = pos;
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }
    public int getPos() {
        return pos;
    }
    public char getSymbol() {
        return symbol;
    }
    public void setPos(Integer pos) {
        this.pos = pos;
    }
}
