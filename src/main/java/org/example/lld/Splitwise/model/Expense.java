package org.example.lld.Splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class Expense {
    private Double amount;
    private User payer;
    private List<Split> splits;
    private String desc;
    //Other metadata
}
