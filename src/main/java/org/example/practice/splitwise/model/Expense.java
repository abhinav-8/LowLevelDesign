package org.example.practice.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Expense {
    private String payerId;
    private double amount;
    private List<Split> splitList;
}
