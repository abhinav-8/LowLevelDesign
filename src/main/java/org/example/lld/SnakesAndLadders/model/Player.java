package org.example.lld.SnakesAndLadders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Player {
    String name;
    String color;
    int position;
    int rewards;
}