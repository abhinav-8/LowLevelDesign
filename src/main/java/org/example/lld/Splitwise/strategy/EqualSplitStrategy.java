package org.example.lld.Splitwise.strategy;

import lombok.Getter;
import lombok.Setter;
import org.example.lld.Splitwise.model.Split;
import org.example.lld.Splitwise.model.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EqualSplitStrategy implements SplitStrategy {

    public List<Split> calculate(User payer, Double amount, List<User> participants, List<Double> values) {
        int n = participants.size();
        Double share = Math.round((amount / n) * 100.0) / 100.0;
        List<Split> splitList = new ArrayList<>();

        for (User participant : participants) {
            splitList.add(new Split(participant, share));
        }

        return splitList;
    }
}
