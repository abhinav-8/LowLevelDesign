package org.example.lld.SnakesAndLadders.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.lld.SnakesAndLadders.model.Player;

@Getter
@Setter
@AllArgsConstructor
public class Coin implements IGameEntity {
    int position;
    int reward;

    @Override
    public boolean apply(Player player) {
        if (player.getPosition() == position){
            player.setRewards(player.getRewards() + reward);
            return true;
        }
        return false;
    }
}
