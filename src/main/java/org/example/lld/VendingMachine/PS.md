## Problem Statement:

Design a low-level vending machine system that simulates the process of selecting a product, inserting money, and receiving the product along with any change. The system should support multiple product types with varying prices and quantities, and be able to handle different denominations of currency.

**Core Requirements:**

- Product Selection: Users should be able to select a product from a displayed inventory.
- Payment Processing: The system must accept various denominations of coins/notes and validate the inserted amount against the product price.
- Product Dispensing: Once sufficient payment is made, the selected product should be dispensed.
- Change Management: The system must calculate and return the correct change. If exact change cannot be provided, the transaction should be cancelled and the inserted money returned.
- Inventory Management: Track product quantities and prevent selection of out-of-stock items.
- Error Handling: Gracefully handle insufficient funds, out-of-stock items, and invalid selections.

**Additional (Potential 'Weird') Requirements:**

- Support for 'combo' purchases (e.g., drink + snack at a discounted price).
- Ability to restock products and refill change.
- Maintenance mode for technicians.
- Integration with a remote monitoring system for sales and stock levels.
- Support for multiple payment methods (e.g., card payments, mobile payments) beyond cash, though this can be simplified for the LLD.

**Design Considerations:**

- Use Object-Oriented Programming (OOP) principles.
- Identify key classes and their interactions (e.g., VendingMachine, Product, Coin, State).
- Consider state management for the vending machine (e.g., Idle, HasSelection, AcceptingMoney, DispensingProduct, ReturningChange).
- Discuss potential design patterns (e.g., State, Strategy, Factory).
- Focus on extensibility for adding new products, payment methods, or features.

# Vending Machine LLD (Final Revision - With Full Logic)

## Flow

Idle → AcceptingMoney → ItemSelection → Dispensing → ReturningChange → Idle

---

## model

```java
enum Denomination {
    ONE(1), TWO(2), FIVE(5), TEN(10);
    int value;
    Denomination(int v){ this.value = v; }
}
```

```java
class Item {
    int id, price;
    String name;

    Item(int id, int price, String name){
        this.id=id; this.price=price; this.name=name;
    }
}
```

```java
class Slot {
    int code;
    Item item;
    int quantity;

    Slot(int code, Item item, int q){
        this.code=code; this.item=item; this.quantity=q;
    }

    boolean isAvailable(){ return quantity > 0; }
    void decrement(){ quantity--; }
}
```

```java
class Inventory {
    Map<Integer, Slot> slots = new HashMap<>();

    void addSlot(Slot s){ slots.put(s.code, s); }
    Slot getSlot(int code){ return slots.get(code); }
}
```

---

## service

```java
class VendingMachine {

    State state = new IdleState();

    Inventory inventory;
    List<Denomination> inserted = new ArrayList<>();
    Map<Denomination,Integer> cash = new HashMap<>();
    Map<Denomination,Integer> changeMap;

    int selected = -1;

    VendingMachine(Inventory inv){
        this.inventory = inv;
    }

    void insertMoney(Denomination d){ state.insertMoney(this, d); }
    void selectItem(int code){ state.selectProduct(this, code); }
    void dispense(){ state.dispense(this); }
    void refund(){ state.refund(this); }

    int total(){
        int sum = 0;
        for(Denomination d: inserted) sum += d.value;
        return sum;
    }

    void addCash(List<Denomination> list){
        for(Denomination d: list)
            cash.put(d, cash.getOrDefault(d,0)+1);
    }

    int deduct(Map<Denomination,Integer> map){
        int val=0;
        for(Denomination d: map.keySet()){
            cash.put(d, cash.get(d)-map.get(d));
            val += d.value * map.get(d);
        }
        return val;
    }

    Map<Denomination,Integer> getChange(int amt){
        Map<Denomination,Integer> res = new HashMap<>();
        Denomination[] vals = Denomination.values();

        for(int i=vals.length-1;i>=0;i--){
            Denomination d = vals[i];
            int count = cash.getOrDefault(d,0);

            while(amt>=d.value && count>0){
                amt-=d.value;
                count--;
                res.put(d,res.getOrDefault(d,0)+1);
            }
        }
        return amt==0 ? res : null;
    }

    void reset(){
        inserted.clear();
        selected = -1;
        changeMap = null;
    }

    void setState(State s){ state = s; }
}
```

---

## state

```java
interface State {
    void insertMoney(VendingMachine vm, Denomination d);
    void selectProduct(VendingMachine vm, int code);
    void dispense(VendingMachine vm);
    void refund(VendingMachine vm);
}
```

---

### IdleState

```java
class IdleState implements State {

    public void insertMoney(VendingMachine vm, Denomination d){
        vm.inserted.add(d);
        vm.setState(new AcceptingMoneyState());
    }

    public void selectProduct(VendingMachine vm,int c){}
    public void dispense(VendingMachine vm){}
    public void refund(VendingMachine vm){}
}
```

---

### AcceptingMoneyState

```java
class AcceptingMoneyState implements State {

    public void insertMoney(VendingMachine vm, Denomination d){
        vm.inserted.add(d);
    }

    public void selectProduct(VendingMachine vm,int code){
        vm.selected = code;
        vm.setState(new ItemSelectionState());
    }

    public void dispense(VendingMachine vm){}
    public void refund(VendingMachine vm){
        vm.reset();
        vm.setState(new IdleState());
    }
}
```

---

### ItemSelectionState

```java
class ItemSelectionState implements State {

    public void insertMoney(VendingMachine vm, Denomination d){
        vm.inserted.add(d);
    }

    public void selectProduct(VendingMachine vm,int code){
        vm.selected = code;
    }

    public void dispense(VendingMachine vm){

        Slot slot = vm.inventory.getSlot(vm.selected);

        if(slot==null || !slot.isAvailable()) return;

        int total = vm.total();
        int price = slot.item.price;

        if(total < price) return;

        int change = total - price;

        Map<Denomination,Integer> map = vm.getChange(change);

        if(map == null){
            refund(vm);
            return;
        }

        vm.changeMap = map;

        vm.setState(new DispenseProductState());
        vm.dispense();
    }

    public void refund(VendingMachine vm){
        vm.reset();
        vm.setState(new IdleState());
    }
}
```

---

### DispenseProductState

```java
class DispenseProductState implements State {

    public void insertMoney(VendingMachine vm, Denomination d){}
    public void selectProduct(VendingMachine vm,int c){}

    public void dispense(VendingMachine vm){

        Slot slot = vm.inventory.getSlot(vm.selected);

        // ✅ business logic
        slot.decrement();

        vm.setState(new ReturnChangeState());
        vm.dispense();
    }

    public void refund(VendingMachine vm){}
}
```

---

### ReturnChangeState

```java
class ReturnChangeState implements State {

    public void insertMoney(VendingMachine vm, Denomination d){}
    public void selectProduct(VendingMachine vm,int c){}

    public void dispense(VendingMachine vm){

        // ✅ add inserted money to machine
        vm.addCash(vm.inserted);

        // ✅ deduct change
        vm.deduct(vm.changeMap);

        vm.reset();
        vm.setState(new IdleState());
    }

    public void refund(VendingMachine vm){}
}
```

---

## Key Points

* Validation happens before dispensing
* Change must be available else refund
* Dispense = workflow progression
* State pattern avoids conditionals
* Business logic preserved (cash + change)


