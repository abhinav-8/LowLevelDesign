package org.example.practice.snakeAndLadder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class User {
    String id;
    String name;
    String color;
    int currPos;
    //Other metadata
}
