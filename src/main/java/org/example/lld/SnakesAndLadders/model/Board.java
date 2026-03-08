package org.example.lld.SnakesAndLadders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.lld.SnakesAndLadders.entity.IGameEntity;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Board {
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