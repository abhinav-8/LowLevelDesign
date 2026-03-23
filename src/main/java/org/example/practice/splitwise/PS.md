### Sample Input
- EXPENSE 1 1000 4 1 2 3 4 EQUAL
- SHOW
- SHOW 1
- SHOW 2
- EXPENSE 4 1000 2 1 3 EXACT 750 250
- SHOW
- SHOW 1
- EXPENSE Abhinav 1000 4 Abhinav Abhishek Sounak Kartike EQUAL
- EXPENSE Sounak 1000 2 Abhinav Abhishek EXACT 750 250

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

class User {
    String id;
    String name;
    //metadata
}

enum ExpenseSplitType {
    EQUAL,
    EXACT,
    PERCENTAGE
}

interface SplitStrategy {
    void calculate();
}

class EqualSplitStrategy implements SplitStrategy{
    
}

class ExactSplitStrategy implements SplitStrategy {
    
}

class Split {
    User user;
    double amount;
}

class Expense {
    String payerId;
    double amount;
    List<Split> splitList;
}
```