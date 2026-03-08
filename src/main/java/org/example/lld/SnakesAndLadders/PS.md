Snakes and Ladders
- 2 players
- There is a board of 100 cells(10X10).Each cell has a number.
- There is a dice as well.
- In each turn, player throws a dice and moves forward by the steps came on the dice.
- Player start from position 1.
- There are snakes and ladders on the board. Snakes take u back and ladder moves u forward.
- Whoever reaches 100 first, wins the game.

3 Steps:
- Classes: list down all the classes.
- Properties of those classes.
- Then u write methods - top to bottom.

OCP:
 * When new requirements come in, you should be able to extend your design without modifying your existing class.

```java
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//Classes -> User, Board, Dice, Cell

@Getter
@Setter
@AllArgsConstructor
class Player {
    String name;
    String color;
    int position;
    int rewards;
}

@AllArgsConstructor
@Getter
@Setter
class Board {
    int size;
    List<IGameEntity> entities;

    public void applyEntities(int moves, Player player) {
        if (player.getPosition() + moves > this.size) return;
        player.setPosition(player.getPosition() + moves);

        for (IGameEntity entity : entities) {
            entity.apply(player);
        }
    }
}

interface DiceStrategy {
    int roll();
}


@Getter
@Setter
@AllArgsConstructor
class Dice implements DiceStrategy {
    int faces;

    public int roll() {
        return (int) (Math.random() * faces) + 1;
    }
}


interface IGameEntity {
    void apply(Player player);
}

@Getter
@Setter
@AllArgsConstructor
class Snake implements IGameEntity {
    int start;
    int end;

    @Override
    public void apply(Player player) {
        if (player.getPosition() == start)
            player.setPosition(end);
    }
}

@Getter
@Setter
@AllArgsConstructor
class Ladder implements IGameEntity {
    int start;
    int end;

    @Override
    public void apply(Player player) {
        if (player.getPosition() == start)
            player.setPosition(end);
    }
}

@Getter
@Setter
@AllArgsConstructor
class Coin implements IGameEntity {
    int position;
    int reward;

    @Override
    public void apply(Player player) {
        if (player.getPosition() == position)
            player.setRewards(player.getRewards() + reward);
    }
}

interface IWinningStrategy {
    boolean didPlayerWin(Player player, Board board);
}

class ReachEndWinningStrategy implements WinningStrategy {
    boolean didPlayerWin(Player player, Board board) {
        return player.getPosition() == board.getSize();
    }
}

class ReachEndAnd10CoinsWinningStrategy implements WinningStrategy {
    boolean didPlayerWin(Player player, Board board) {
        return player.getPosition() == board.getSize() || player.getRewards() == 10;
    }
}

interface ITurnStrategy {
    int nextTurn(int currPlayerIndex, int noOfPlayers);
}

class RandomTurn implements ITurnStrategy {
    @Override
    public int nextTurn(int currPlayerIndex, int noOfPlayers) {
        return (int) (Math.random()* noOfPlayers) + 1;
    }
}

class RoundRobin implements ITurnStrategy {
    @Override
    public int nextTurn(int currPlayerIndex, int noOfPlayers) {
        return (currentPlayerIndex + 1) % noOfPlayers;
    }
}


@AllArgsConstructor
class Game {
    Board board;
    List<Player> players;
    Dice dice;
    int currentPlayerIndex;
    IWinningStrategy winningStrategy;
    ITurnStrategy turn;

    void startGame() {
        while (true) {
            int moves = dice.roll();
            Player currentPlayer = players[currPlayerIndex];
            board.applyEntities(moves, currentPlayer);

            if (winningStrategy.didPlayerWin(currentPlayer)) {
                System.out.println("Player: " + currentPlayer.getName() + "is winner");
                return;
            }

            currentPlayerIndex = turn.nextTurn(currentPlayerIndex, players.size());
        }
    }

}

void main(String[] args) {
    List<IGameEntity> entities = Arrays.asList(new Snake(40, 10), new Snake(92, 48), new Ladder(19, 60), new Ladder(32, 88), new Coin(10, 4), new Coin(5, 8));
    Board board = new Board(100, entities);
    List<Player> players = Arrays.asList(new Player("A", "blue", 0, 0), new Player("B", "red", 0, 0));
    Dice dice = new Dice(6);
    ReachEndWinningStrategy reachEndWinningStrategy = new ReachEndWinningStrategy();
    RoundRobin roundRobin = new RoundRobin();
    Game game = new Game(board, players, dice, 0, reachEndWinningStrategy, roundRobin);
    game.startGame();
}
```