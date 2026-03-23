package org.example.practice.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Split {
    private User user;
    private double share;
}
