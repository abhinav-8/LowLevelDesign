### Sample Input
- EXPENSE 1 1000 4 1 2 3 4 EQUAL
- SHOW
- SHOW 1
- SHOW 2
- EXPENSE 4 1000 2 1 3 EXACT 750 250
- SHOW
- SHOW 1

### Sample Output
- 2 owes 1: 250.0
- 3 owes 1: 250.0
- 4 owes 1: 250.0
-------
- 2 owes 1: 250.0
- 3 owes 1: 250.0
- 4 owes 1: 250.0
------
- 2 owes 1: 250.0
------
- 1 owes 4: 500.0
- 2 owes 1: 250.0
- 3 owes 1: 250.0
- 3 owes 4: 250.0
------
- 2 owes 1: 250.0
- 3 owes 1: 250.0
- 1 owes 4: 500.0

### Requirements:

User: Each user should have a userId, name , email, mobile number.

Expense: Could either be EQUAL, EXACT

Users can add any amount, select any type of expense, and split with any of the available users.

In the case of exact, you need to verify if the total sum of shares is equal to the total amount or not.

The application should have the capability to show expenses for a single user as well as balances for everyone.

When asked to show balances, the application should show the balances of a user with all the users where there
is a non-zero balance.

The amount should be rounded off to two decimal places. Say if User1 paid 100 and amount is split equally
among 3 people. Assign 33.34 first-person and 33.33 to others.

```java
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Objects;

interface Observer {
    void update(String message);
}

@Getter
@Setter
@AllArgsConstructor
class User implements Observer {
    private String id;
    private String name;
    private String email;
    private String phone;
    //Other metadata

    @Override
    public void update(String message) {
        System.out.println("Notify " + id + ": " + message);
    }
}

enum ExpenseSplitType {
    EQUAL,
    EXACT,
    PERCENTAGE
}

@Getter
@Setter
@AllArgsConstructor
class Split {
    private User participant;
    private Double share;
}

interface SplitStrategy {
    List<Split> calculate(User payer, Double amount, List<User> participants, List<Double> values);
}

class EqualSplitStrategy implements SplitStrategy {
    public List<Split> calculate(User payer, Double amount, List<User> participants, List<Double> values) {
        int n = participants.size();
        Double share = Math.round((amount / n) * 100.0) / 100.0;
        List<Split> splitList = new ArrayList<>();

        for (int i = 0; i < participants.size(); i++) {
            splitList.add(new Split(participants.get(i), share));
        }

        return splitList;
    }
}

class ExactSplitStrategy implements SplitStrategy {
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

class StrategyFactory {
    public SplitStrategy getStrategy(ExpenseSplitType splitType) {
        if (splitType == ExpenseSplitType.EQUAL) return new EqualSplitStrategy();
        else if (splitType == ExpenseSplitType.EXACT) return new ExactSplitStrategy();
        throw new RuntimeException("Unsupported split type!");
    }
}

@Getter
@Setter
@AllArgsConstructor
class Expense {
    private Double amount;
    private User payer;
    private List<Split> splits;
    private String desc;
    //Other metadata
}

class BalanceSheet {
    Map<String, Map<String, Double>> balances = new HashMap<>();
    List<Observer> observers = new ArrayList<>();

    void addObserver(Observer observer) {
        observers.add(observer);
    }

    void notifyAllObservers(String msg) {
        for (Observer u : observers) {
            u.update(msg);
        }
    }

    public void addExpense(Expense expense) {
        String payerId = expense.getPayer().getId();
        List<Split> splits = expense.getSplits();
        for (Split split : splits) {
            String participantId = split.getParticipant().getId();
            if (Objects.equals(payerId, participantId)) continue;
            balances.putIfAbsent(payerId, new HashMap<>());
            balances.putIfAbsent(participantId, new HashMap<>());

            balances.get(payerId).put(
                    participantId,
                    balances.get(payerId).getOrDefault(participantId, 0.0) - split.getShare()
            );

            balances.get(participantId).put(
                    payerId,
                    balances.get(participantId).getOrDefault(payerId, 0.0) + split.getShare()
            );

            notifyAllObservers(participantId + " owes " + payerId + ": " + split.getShare());
        }
    }

    public void showBalances() {
        for (String u1 : balances.keySet()) {
            for (String u2 : balances.get(u1).keySet()) {
                Double amount = balances.get(u1).get(u2);
                if (amount > 0) {
                    System.out.println(u1 + " owes " + u2 + ": " + amount);
                }
            }
        }
    }

    public void showBalancesForUser(String userId) {

        if (!balances.containsKey(userId)) {
            System.out.println("No balances");
            return;
        }

        for (String u2 : balances.get(userId).keySet()) {
            Double amount = balances.get(userId).get(u2);
            if (amount > 0) {
                System.out.println(userId + " owes " + u2 + ": " + amount);
            } else if (amount < 0) {
                System.out.println(u2 + " owes " + userId + ": " + Math.abs(amount));
            }
        }
    }
}

class SplitWiseService {
    StrategyFactory strategyFactory = new StrategyFactory();
    BalanceSheet balanceSheet = new BalanceSheet();
    Map<String, User> userMap = new HashMap<>();

    public void addUser(String id, String name) {
        User user = new User(id, name, "", "");
        userMap.put(id, user);
        balanceSheet.addObserver(user);
    }

    public void addExpense(String payerId, Double amount, ExpenseSplitType splitType, List<String> participantIds, List<Double> expenseVals, String description) {
        SplitStrategy strategy = strategyFactory.getStrategy(splitType);
        List<User> participants = new ArrayList<>();
        for (String id : participantIds) {
            participants.add(userMap.get(id));
        }
        Expense expense = new Expense(amount, userMap.get(payerId), strategy.calculate(userMap.get(payerId), amount, participants, expenseVals), description);
        balanceSheet.addExpense(expense);
    }

    public void showAll() {
        balanceSheet.showBalances();
    }

    public void showUser(String userId) {
        balanceSheet.showBalancesForUser(userId);
    }
}

interface Command {
    void execute();
}

@AllArgsConstructor
class ShowCommand implements Command {
    private SplitWiseService service;
    private String[] inputParts;
//    SHOW
//    SHOW user1
    @Override
    public void execute() {
        if(inputParts.length == 1) {
            service.showAll();
        }
        else{
            service.showUser(inputParts[1]);       
        }
    }
}

@AllArgsConstructor
class ExpenseCommand implements Command {
    private SplitWiseService service;
    private String[] inputParts;
//    EXPENSE user1 1000 4 user1 user2 user3 user4 EQUAL
//    EXPENSE user1 1250 2 user2 user3 EXACT 370 880
    @Override
    public void execute() {
        String payerId = inputParts[1];
        Double amount = Double.parseDouble(inputParts[2]);
        int n = Integer.parseInt(inputParts[3]);
        List<String> participantIds = new ArrayList<>();
        List<Double> expenseVal = new ArrayList<>();
        int i;
        for(i = 4 ; i <= 4+n ; i++){
            participantIds.add(inputParts[i]);
        }
        String type = inputParts[i++];
        ExpenseSplitType splitType = ExpenseSplitType.valueOf(type);
        while(i < inputParts.length){
            expenseVal.add(Double.parseDouble(inputParts[i]));
            i++;
        }
        service.addExpense(payerId,amount, splitType, participantIds, expenseVal, "TEST");
    }
}

@AllArgsConstructor
class CommandLineManager {
    SplitWiseService service;
//    EXPENSE user1 1000 4 user1 user2 user3 user4 EQUAL
//    EXPENSE user1 1250 user2 user3 EXACT 370 880
//    SHOW
//    SHOW user1
    public void execute(String commandInput) {
        final String[] inputParts = commandInput.split(" ");

        //Validate the input format
        String commandName = inputParts[0];
        Command command;
        if(Objects.equals(commandName, "SHOW")){
            command = new ShowCommand(service, inputParts);
        } else {
            command = new ExpenseCommand(service, inputParts);
        }
        
        command.execute();
    }
}
```

### Explanation:
The system takes CLI input, delegates it to command handlers,
uses strategy to compute splits, updates balances in a central repository,
and notifies users via observer pattern.

- Command pattern handles input parsing
- Strategy pattern handles split logic
- Service layer orchestrates flow
- BalanceSheet stores and updates balances
- Observer pattern handles notifications