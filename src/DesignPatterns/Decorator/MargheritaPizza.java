package DesignPatterns.Decorator;

public class MargheritaPizza implements Pizza{
    @Override
    public String getDescription() {
        return "Margherita";
    }

    @Override
    public double getPrice() {
        return 30;
    }
}
