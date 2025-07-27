package DesignPatterns.Decorator;

public class Olives extends PizzaDecorator{
    public Olives(Pizza p) {
        super(p);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olives";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 2.3;
    }
}
