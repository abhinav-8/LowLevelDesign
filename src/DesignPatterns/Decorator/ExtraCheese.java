package DesignPatterns.Decorator;

public class ExtraCheese extends PizzaDecorator{

    public ExtraCheese(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Extra Cheese";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 0.5;
    }
}
