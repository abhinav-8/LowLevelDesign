package org.example.lld.SnakesAndLadders.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.lld.SnakesAndLadders.model.Player;

@Getter
@Setter
@AllArgsConstructor
public class Ladder implements IGameEntity {
    int start;
    int end;

    @Override
    public boolean apply(Player player) {
        if (player.getPosition() == start) {
            System.out.println("-----------------Player: " + player.getName() + " moved using ladder from " + start + " to " + end + "-----------------");
            player.setPosition(end);
            return true;
        }
        return false;
    }

}
