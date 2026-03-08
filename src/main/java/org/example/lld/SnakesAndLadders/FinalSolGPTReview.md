# Snakes and Ladders

* 2 players
* There is a board with **100 cells (10×10)**. Each cell has a number.
* There is a **dice** as well.
* In each turn, player throws a dice and moves forward by the steps that come on the dice.
* Players start from **position 1**.
* There are **snakes and ladders** on the board.

    * Snake → moves player backward
    * Ladder → moves player forward
* Whoever reaches **100 first wins the game**.

---

# Steps

### 1. Classes

```id="c1"
Player
Board
Dice
Game
Snake
Ladder
Coin
IGameEntity
WinningStrategy
TurnStrategy
```

---

### 2. Properties of Classes

**Player**

```id="c2"
name
color
position
rewards
```

**Board**

```id="c3"
size
List<IGameEntity> entities
```

**Dice**

```id="c4"
faces
```

**Snake**

```id="c5"
start
end
```

**Ladder**

```id="c6"
start
end
```

**Coin**

```id="c7"
position
reward
```

---

# OCP

When new requirements come in, we should be able to extend the system **without modifying existing classes**.

To support this we introduce:

```id="c8"
IGameEntity
```

Now Snake, Ladder, Coin etc. implement the same interface.

---

# Java Implementation

```java
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
            if(entity.apply(player))
                break;
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
    boolean apply(Player player);
}

@Getter
@Setter
@AllArgsConstructor
class Snake implements IGameEntity {
    int start;
    int end;

    public boolean apply(Player player) {
        if (player.getPosition() == start) {
            player.setPosition(end);
            return true;
        }
        return false;
    }
}

@Getter
@Setter
@AllArgsConstructor
class Ladder implements IGameEntity {
    int start;
    int end;

    public boolean apply(Player player) {
        if (player.getPosition() == start) {
            player.setPosition(end);
            return true;
        }
        return false;
    }
}

@Getter
@Setter
@AllArgsConstructor
class Coin implements IGameEntity {
    int position;
    int reward;

    public boolean apply(Player player) {
        if (player.getPosition() == position) {
            player.setRewards(player.getRewards() + reward);
            return true;
        }
        return false;
    }
}

interface IWinningStrategy {
    boolean didPlayerWin(Player player, Board board);
}

class ReachEndWinningStrategy implements IWinningStrategy {
    public boolean didPlayerWin(Player player, Board board) {
        return player.getPosition() == board.getSize();
    }
}

class ReachEndAnd10CoinsWinningStrategy implements IWinningStrategy {
    public boolean didPlayerWin(Player player, Board board) {
        return player.getPosition() == board.getSize() && player.getRewards() >= 10;
    }
}

interface ITurnStrategy {
    int nextTurn(int currPlayerIndex, int noOfPlayers);
}

class RandomTurn implements ITurnStrategy {
    public int nextTurn(int currPlayerIndex, int noOfPlayers) {
        return (int) (Math.random() * noOfPlayers);
    }
}

class RoundRobin implements ITurnStrategy {
    public int nextTurn(int currPlayerIndex, int noOfPlayers) {
        return (currPlayerIndex + 1) % noOfPlayers;
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
            Player currentPlayer = players.get(currentPlayerIndex);

            board.applyEntities(moves, currentPlayer);

            if (winningStrategy.didPlayerWin(currentPlayer, board)) {
                System.out.println("Player: " + currentPlayer.getName() + " is winner");
                return;
            }

            currentPlayerIndex = turn.nextTurn(currentPlayerIndex, players.size());
        }
    }
}

public class SnakeLadderApplication {
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
}

```

---

# Design Patterns Used

**1. Strategy Pattern**

Used for behaviours that can change.

Examples:

```id="p1"
DiceStrategy
IWinningStrategy
ITurnStrategy
```

Allows changing:

* dice behaviour
* winning rule
* turn order

without modifying `Game`.

---

**2. Open Closed Principle**

Board depends on:

```id="p2"
IGameEntity
```

So new entities can be added like:

```id="p3"
Dragon
Trap
Teleport
Butterfly
```

without modifying Board.

---

**3. Single Responsibility Principle**

Responsibilities separated:

```id="p4"
Player → player state
Board → board mechanics
Game → gameplay flow
Entities → board behaviour
Strategies → rule variations
```

---

# Current Limitations

**1. Entity lookup is O(n)**

Board scans all entities each move:

```id="l1"
for(entity : entities)
```

Better approach:

```id="l2"
Map<Integer, List<IGameEntity>>
```

---

**2. Board handles player movement**

Currently:

```id="l3"
Board.applyEntities()
```

moves player and applies entity.

Cleaner design:

```id="l4"
Game moves player
Board applies entity
```

---

**3. Only one entity applied per move**

Because we break after first match.

Example limitation:

```id="l5"
Coin + Ladder on same cell
```

Only one will trigger.

---

**4. Dice strategy is minimal**

Currently only single dice.

Future extensions:

```id="l6"
multiple dice
roll again on 6
special dice rules
```

---

**5. No support for skipping dead players**

If future entity like:

```id="l7"
Dragon → kills player
```

turn strategy must handle skipped players.
