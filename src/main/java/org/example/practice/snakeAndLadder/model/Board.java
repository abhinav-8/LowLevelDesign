package org.example.practice.snakeAndLadder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.practice.snakeAndLadder.entity.IGameEntity;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Board {
    private int size;
    private List<IGameEntity> entities;

    public void movePlayer(User player, int roll) {
        System.out.println(player.getName() + " got: " + roll);
        if(player.getCurrPos() + roll > size) return;
        player.setCurrPos(player.getCurrPos() + roll);

        for (IGameEntity entity : entities) {
            int val = entity.apply(player.getCurrPos());
            if (val != -1) {
                player.setCurrPos(val);
                break;
            }
        }
        System.out.println(player.getName() + " moved to " + player.getCurrPos());
    }
}
