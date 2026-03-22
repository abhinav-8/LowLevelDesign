package org.example.lld.Splitwise.strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.lld.Splitwise.model.Split;
import org.example.lld.Splitwise.model.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExactSplitStrategy implements SplitStrategy {
    public List<Split> calculate(User payer, Double amount, List<User> participants, List<Double> values) {
        Double sum = 0.0;
        for (Double value : values) sum += value;

        if (Math.abs(sum - amount) > 0.01) {
            throw new RuntimeException("Invalid distribution of amount");
        }
        List<Split> splitList = new ArrayList<>();

        for (int i = 0; i < participants.size(); i++) {
            splitList.add(new Split(participants.get(i), values.get(i)));
        }

        return splitList;
    }
}
