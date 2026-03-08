package org.example.lld.SnakesAndLadders.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.lld.SnakesAndLadders.model.Player;

@Getter
@Setter
@AllArgsConstructor
public class Snake implements IGameEntity {
    int start;
    int end;

    @Override
    public boolean apply(Player player) {
        if (player.getPosition() == start) {
            System.out.println("-----------------Player: " + player.getName() + " ate by snake and moved from " + start + " to " + end + "----------------");
            player.setPosition(end);
            return true;
        }
        return false;
    }
}