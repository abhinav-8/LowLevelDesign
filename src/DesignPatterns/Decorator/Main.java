package DesignPatterns.Decorator;

public class Main {
    public static void main(String[] args) {
        Pizza pizza = new MargheritaPizza();
        pizza = new ExtraCheese(pizza);
        pizza = new Olives(pizza);

        System.out.println(pizza.getDescription());
        System.out.println(pizza.getPrice());
    }
}
