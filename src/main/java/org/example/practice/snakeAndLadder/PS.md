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

```java
class User {
    String id;
    String name;
    String color;
    int currPos;
    //Other metadata
}
class Dice {
    int noOfFaces;
}

class Ladder {
  int start;
  int end;
}
class Snake {
    int start;
    int end;
}
class Board {
    int n;
    List<Snake> snakes;
    List<Ladder> ladders;
}

class Turn {
    int nextTurn() {
        
    }
}

class Game {
    Board board;
    List<User> players;
    Dice dice;
    int currPlayerIndex;
}

void main(String[] args) {
    U
}
```