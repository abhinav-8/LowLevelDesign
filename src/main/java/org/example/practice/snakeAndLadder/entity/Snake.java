package org.example.practice.snakeAndLadder.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Snake implements IGameEntity {
    private int start;
    private int end;
    private String desc;

    @Override
    public int apply(int userPosition) {
        if(userPosition == start) {
            System.out.println("Got snake, moved from " + start + " to " + end);
            return end;
        }
        return -1;
    }
}
